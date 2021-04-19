package com.example.mycinema

import com.example.mycinema.model.Result

sealed class AppState {
    data class Success(val results: List<Result>) : AppState()
    data class SuccessLoadDetails(val result: Result) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}