package com.example.mycinema.ui.view.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mycinema.AppState
import com.example.mycinema.model.Result
import com.example.mycinema.R
import com.example.mycinema.databinding.MainFragmentBinding
import com.example.mycinema.ui.view.MainViewModel
import com.example.mycinema.ui.view.details.FragmentDetails
import com.example.mycinema.ui.view.favorites.FragmentFavorites
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private val adapter by lazy {
        MainFragmentAdapter(onItemClickListener)
    }

    private val bottomNavigationMenu by lazy {
        binding.bottomNavigation
    }

    private val onItemClickListener = fun(result: Result) {
        startFragmentDetails(result.id)
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        setOnBottomMenuItemSelectedListener()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = binding.rvMain
        recyclerView.adapter = adapter
        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter.removeListener()
    }

    private fun startFragmentDetails(filmId: Int) {
        val bundle = Bundle()
        bundle.putInt(FragmentDetails.BUNDLE_EXTRA, filmId)
        startNewFragment(FragmentDetails.newInstance(bundle))
    }

    private fun startFragmentFavorites(){
        val bundle = Bundle()
        startNewFragment(FragmentFavorites.newInstance(bundle))
    }

    private fun startNewFragment(newInstanceFragment: Fragment){
        val manager = requireActivity().supportFragmentManager
        manager.beginTransaction().replace(R.id.container, newInstanceFragment)
            .addToBackStack("").commit()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                adapter.filmsData = appState.results
                binding.loadingLayout.visibility = View.GONE
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getFilmsFromLocalSourceRus() }
                    .show()
            }
        }
    }

    private fun setOnBottomMenuItemSelectedListener(){
        bottomNavigationMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.favorite_page -> {
                    startFragmentFavorites()
                    true
                }
                else -> false
            }
        }

        bottomNavigationMenu.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.favorite_page -> {
                    Log.d("=====", "reset favorite page!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                    startFragmentFavorites()
                }
            }
        }
    }
}