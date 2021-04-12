package com.example.mycinema.ui.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mycinema.model.Result
import com.example.mycinema.databinding.FragmentFilmDetailsBinding
import com.example.mycinema.model.repository.DB
import com.example.mycinema.model.repository.Repository

const val API_KEY = "8c8edb0d2bfa7286cf1ca894bf268ea5"

class FragmentDetails : Fragment() {
    private val db: Repository = DB
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    private var filmID = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        initId()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = db.getFilmByID(filmID)
        displayFilms(result)
    }

    companion object {
        const val BUNDLE_EXTRA = "film"
        fun newInstance(bundle: Bundle): FragmentDetails {
            val fragment = FragmentDetails()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun displayFilms(result: Result?) {
        if (result != null)
            with(binding) {
                filmTitle.text = result.title
                filmDescription.text = result.overview
                filmRating.text = result.vote_average.toString()
            }
    }

    private fun initId() {
        filmID = arguments?.getInt(BUNDLE_EXTRA) ?: 0
    }
}