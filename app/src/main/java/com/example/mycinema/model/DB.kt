package com.example.mycinema.model

object DB : Repository {
    private val db: ArrayList<Film> = arrayListOf(
        Film(550,"film1", "film description",  1f),
        Film(200,"film2", "film description",  1f),
        Film(300,"film3", "film description",  1f),
        Film(400,"film4", "film description",  1f),
        Film(500,"film5", "film description",  1f),
        Film(600,"film6", "film description",  1f),
        Film(800,"film7", "film description",  1f),
        Film(850,"film8", "film description",  1f),
        Film(900,"film9", "film description",  1f),
        Film(150,"film10", "film description", 1f),
        Film(110,"film11", "film description", 1f),
        Film(120,"film12", "film description", 1f),
        Film(130,"film13", "film description", 1f),
        Film(140,"film14", "film description", 1f),
        Film(155,"film15", "film description", 1f),
        Film(160,"film16", "film description", 1f),
        Film(170,"film17", "film description", 1f),
        Film(180,"film18", "film description", 1f),
        Film(190,"film19", "film description", 1f),
        Film(250,"film20", "film description", 1f)
    )

    override fun getFilmsFromServer(): ArrayList<Film> {
        return db
    }

    override fun getFilmsFromLocalStorage(): ArrayList<Film> {
        return db
    }
}