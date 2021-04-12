package com.example.mycinema.model.repository

import com.example.mycinema.model.ListFilms
import com.example.mycinema.model.Result
import retrofit2.Callback

object DB : Repository {
    private val remoteDataSource = RemoteDataSource()
    private val DB: ArrayList<Result> = arrayListOf()

    override fun getFilmsFromServer(page: Int, callback: Callback<ListFilms>){
        remoteDataSource.getListFilmsNowPlaying(page, callback)
    }

    override fun getFilmsFromLocalStorage(): ArrayList<Result> {
        return DB
    }

    override fun getFilmByID(id: Int): Result? {
        for (res in DB){
            if (res.id == id)
                return res
        }
        return null
    }
}