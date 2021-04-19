package com.example.mycinema.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmsEntity(
    @PrimaryKey
    val id: Int,
    val backdrop_path: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double
)