package com.example.mycinema

import com.example.mycinema.Model.Film

sealed class AppState {
    data class Success(val films: ArrayList<Film>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}