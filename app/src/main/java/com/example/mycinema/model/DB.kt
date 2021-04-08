package com.example.mycinema.model

import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.mycinema.ui.view.details.API_KEY
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object DB : Repository {
    private val handlerThread = HandlerThread("")
    private val DB: ArrayList<Result> = arrayListOf()

    init {
        handlerThread.start()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getFilmsFromServer(): ArrayList<Result> {
        loadDataFromServer()
        return DB
    }

    override fun getFilmsFromLocalStorage(): ArrayList<Result> {
        return DB
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadDataFromServer() {
        try {
            val uri =
                URL("https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY&language=en-US&page=1")
            val mainHandler = Handler(Looper.getMainLooper())
            val loadHandlerThread = Handler(handlerThread.looper)
            loadHandlerThread.post {
                lateinit var urlConnection: HttpsURLConnection

                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.readTimeout = 10_000

                    val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val films = Gson().fromJson(getLines(reader), ListFilms::class.java)
                    mainHandler.post {
                        DB.clear()
                        DB.addAll(films.results)
                    }
                } catch (e: Exception) {
                    Log.e("", "Fail URI", e)
                    e.printStackTrace()
                } finally {
                    urlConnection.disconnect()
                }
            }
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}