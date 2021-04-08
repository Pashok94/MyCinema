package com.example.mycinema.model

interface Repository {
    fun getFilmsFromServer(): ArrayList<Result>
    fun getFilmsFromLocalStorage(): ArrayList<Result>
}