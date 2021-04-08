package com.example.mycinema

import com.example.mycinema.model.Result

sealed class AppState {
    data class Success(val results: ArrayList<Result>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}