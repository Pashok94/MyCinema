package com.example.mycinema.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val title: String,
    val description: String,
    val rating: Int
    ) : Parcelable