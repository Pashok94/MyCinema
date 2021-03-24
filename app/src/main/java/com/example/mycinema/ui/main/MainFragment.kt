package com.example.mycinema.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycinema.AppState
import com.example.mycinema.Model.Film
import com.example.mycinema.R
import com.example.mycinema.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {
    private var binding: MainFragmentBinding? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = Observer<AppState>{renderData(it)}
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val film = appState.film
                binding!!.loadingLayout.visibility = View.GONE
                Snackbar.make(binding!!.mainView, "Success", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                binding!!.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding!!.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding!!.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getFilmsData() }
                    .show()
            }
        }
    }
}