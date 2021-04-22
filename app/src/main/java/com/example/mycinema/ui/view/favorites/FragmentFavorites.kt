package com.example.mycinema.ui.view.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mycinema.AppState
import com.example.mycinema.databinding.FragmentFavoritesFilmsBinding
import com.example.mycinema.ui.view.main.MainFragmentAdapter
import com.google.android.material.snackbar.Snackbar

class FragmentFavorites : Fragment() {
    private var _binding: FragmentFavoritesFilmsBinding? = null
    private val binding get() = _binding!!

    private val liveData by lazy {
        ViewModelProvider(this).get(FavoritesViewModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        FavoritesFragmentAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesFilmsBinding.inflate(inflater, container, false)

        recyclerView = binding.rwFavorites
        recyclerView.adapter = adapter

        val observer = Observer<AppState> {
            renderData(it)
        }
        liveData.getLiveData().observe(viewLifecycleOwner, observer)

        return binding.root
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                adapter.favoritesFilms = appState.results
            }
        }
    }

    companion object {
        fun newInstance(bundle: Bundle): FragmentFavorites {
            val fragment = FragmentFavorites()
            fragment.arguments = bundle
            return fragment
        }
    }
}