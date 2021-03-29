package com.example.mycinema.model

object DB : Repository {
    private val db: ArrayList<Film> = arrayListOf(
        Film("film1", "film description", 1),
        Film("film2", "film description", 1),
        Film("film3", "film description", 1),
        Film("film4", "film description", 1),
        Film("film5", "film description", 1),
        Film("film6", "film description", 1),
        Film("film7", "film description", 1),
        Film("film8", "film description", 1),
        Film("film9", "film description", 1),
        Film("film10", "film description", 1),
        Film("film11", "film description", 1),
        Film("film12", "film description", 1),
        Film("film13", "film description", 1),
        Film("film14", "film description", 1),
        Film("film15", "film description", 1),
        Film("film16", "film description", 1),
        Film("film17", "film description", 1),
        Film("film18", "film description", 1),
        Film("film19", "film description", 1),
        Film("film20", "film description", 1)
    )

    override fun getFilmsFromServer(): ArrayList<Film> {
        return db
    }

    override fun getFilmsFromLocalStorage(): ArrayList<Film> {
        return db
    }
}