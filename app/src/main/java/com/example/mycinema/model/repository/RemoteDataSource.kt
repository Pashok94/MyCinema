package com.example.mycinema.model.repository

import com.example.mycinema.model.ListFilms
import com.example.mycinema.ui.view.details.API_KEY
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RemoteDataSource {
    private val filmsApi = Retrofit.Builder()
        .baseUrl("https://api.tmdb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .client(createOkHttpClient(FilmsApiInterceptor()))
        .build().create(FilmsApi::class.java)

    fun getListFilmsNowPlaying(page: Int, callback: Callback<ListFilms>){
        filmsApi.getPageFilms(API_KEY, "ru", page, "Ru").enqueue(callback)
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

        inner class FilmsApiInterceptor : Interceptor {

            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                return chain.proceed(chain.request())
            }
        }
}