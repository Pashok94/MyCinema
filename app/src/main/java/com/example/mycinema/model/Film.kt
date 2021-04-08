package com.example.mycinema.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val title: String,
    val overview: String,
    val vote_average: Float
    ) : Parcelable