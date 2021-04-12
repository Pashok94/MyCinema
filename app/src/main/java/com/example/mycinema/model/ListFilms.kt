package com.example.mycinema.model

data class ListFilms(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int
)