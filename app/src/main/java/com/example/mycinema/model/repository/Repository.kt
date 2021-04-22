package com.example.mycinema.model.repository

import com.example.mycinema.model.ListFilms
import com.example.mycinema.model.Result
import retrofit2.Callback

interface Repository {
    fun getFilmsFromServer(page: Int, callback: Callback<ListFilms>)
    fun getFilmsFromLocalStorage(): ArrayList<Result>
    fun saveEntity(film: Result)
    fun getFilmById(id : Int, callback: Callback<Result>)
}