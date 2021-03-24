package com.example.mycinema.Model

object DB : Repository{
    private val db: ArrayList<Film> = arrayListOf(
        Film("film1", "film description", 1),
        Film("film2", "film description", 1),
        Film("film3", "film description", 1),
        Film("film4", "film description", 1),
        Film("film5", "film description", 1)
    )
    override fun getFilmsFromServer(): ArrayList<Film> {
        return db
    }

    override fun getFilmsFromLocalStorage(): ArrayList<Film> {
        return db
    }
}