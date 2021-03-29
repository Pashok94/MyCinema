package com.example.mycinema.model

interface Repository {
    fun getFilmsFromServer(): ArrayList<Film>
    fun getFilmsFromLocalStorage(): ArrayList<Film>
}