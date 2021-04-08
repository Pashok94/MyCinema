package com.example.mycinema.ui.view.details

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.mycinema.model.Film
import com.example.mycinema.databinding.FragmentFilmDetailsBinding
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val API_KEY = "8c8edb0d2bfa7286cf1ca894bf268ea5"

class FragmentDetails : Fragment() {
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    private var filmID = 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        initId()
        loadFilm()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadFilm() {
        try {
            val uri = URL("https://api.themoviedb.org/3/movie/${filmID}?api_key=$API_KEY")
            val handler = Handler(Looper.getMainLooper())
            Thread{
                lateinit var urlConnection: HttpsURLConnection

                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.readTimeout = 10_000

                    val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val film = Gson().fromJson(getLines(reader), Film::class.java)
                    handler.post{
                        displayFilms(film)
                    }
                }catch (e: Exception){
                    Log.e("", "Fail URI", e)
                    e.printStackTrace()
                }finally {
                    urlConnection.disconnect()
                }
            }.start()
        }catch (e: MalformedURLException){
            Log.e("", "Fail URI", e)
            e.printStackTrace()
        }
    }

    private fun displayFilms(film: Film){
        with(binding){
            filmTitle.text = film.title
            filmDescription.text = film.overview
            filmRating.text = film.vote_average.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val film = arguments?.getParcelable<Film>(BUNDLE_EXTRA)
//        if (film != null) {
////            binding.apply {
////                filmTitle.text = film.title
////                filmDescription.text = film.overview
////                filmRating.text = film.vote_average.toString()
////            }
//        }
    }

    companion object {
        const val BUNDLE_EXTRA = "film"
        fun newInstance(bundle: Bundle): FragmentDetails {
            val fragment = FragmentDetails()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun initId(){
        val film = arguments?.getParcelable<Film>(BUNDLE_EXTRA)
        if (film != null)
            filmID = film.id
    }
}