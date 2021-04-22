package com.example.mycinema.ui.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycinema.AppState
import com.example.mycinema.databinding.FragmentFilmDetailsBinding
import com.example.mycinema.model.Result

const val API_KEY = "8c8edb0d2bfa7286cf1ca894bf268ea5"

class FragmentDetails : Fragment() {
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var currentFilm: Result

    private var filmID = 0

    private val btnAddToFavorites by lazy {
        binding.btnAddToFavorites
    }

    private val liveData by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        initId()

        val observer = Observer<AppState> {
            displayFilms(it)
        }
        liveData.getLiveData().observe(viewLifecycleOwner, observer)
        liveData.loadFilmById(filmID)

        btnAddToFavorites.setOnClickListener {
             liveData.addFilmToFavorites(currentFilm)
        }

        return binding.root
    }

    companion object {
        const val BUNDLE_EXTRA = "film"
        fun newInstance(bundle: Bundle): FragmentDetails {
            val fragment = FragmentDetails()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun displayFilms(appState: AppState) {
        when (appState) {
            is AppState.SuccessLoadDetails -> {
                currentFilm = appState.result
                with(binding) {
                    filmTitle.text = currentFilm.title
                    filmDescription.text = currentFilm.overview
                    filmRating.text = currentFilm.vote_average.toString()
                }
            }
        }
    }

    private fun initId() {
        filmID = arguments?.getInt(BUNDLE_EXTRA) ?: 0
    }
}