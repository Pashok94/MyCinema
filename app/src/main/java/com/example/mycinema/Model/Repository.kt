package com.example.mycinema.Model

interface Repository {
    fun getFilmsFromServer(): ArrayList<Film>
    fun getFilmsFromLocalStorage(): ArrayList<Film>
}