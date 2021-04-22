package com.example.mycinema.model.repository

import com.example.mycinema.model.ListFilms
import com.example.mycinema.model.Result
import com.example.mycinema.model.room.FilmsDao
import com.example.mycinema.model.room.FilmsEntity
import retrofit2.Callback

class RepositoryImpl(private val localeDataSource: FilmsDao) : Repository {
    private val remoteDataSource = RemoteDataSource()

    override fun getFilmsFromServer(page: Int, callback: Callback<ListFilms>) {
        remoteDataSource.getListFilmsNowPlaying(page, callback)
    }

    override fun getFilmsFromLocalStorage(): ArrayList<Result> {
        return convertFilmsEntityToFilmsList(localeDataSource.getAll())
    }

    override fun saveEntity(film: Result) {
        localeDataSource.insert(convertFilmsToEntity(film))
    }

    override fun getFilmById(id: Int, callback: Callback<Result>) {
        remoteDataSource.getFilmById(id, callback)
    }

    private fun convertFilmsEntityToFilmsList(entityList: List<FilmsEntity>): ArrayList<Result> {
        return ArrayList(
            entityList.map {
                Result(
                    it.id,
                    it.backdrop_path,
                    it.overview,
                    it.poster_path,
                    it.release_date,
                    it.title,
                    it.vote_average
                )
            }
        )
    }

    private fun convertFilmsToEntity(film: Result): FilmsEntity {
        return FilmsEntity(
            film.id,
            film.backdrop_path,
            film.overview,
            film.poster_path,
            film.release_date,
            film.title,
            film.vote_average
        )
    }
}