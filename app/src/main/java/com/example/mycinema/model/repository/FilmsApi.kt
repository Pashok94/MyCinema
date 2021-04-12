package com.example.mycinema.model.repository

import com.example.mycinema.model.ListFilms
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {
    @GET("3/movie/now_playing")
    fun getPageFilms(
        @Query("api_key") token: String,
        @Query("language") lang: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): Call<ListFilms>
}