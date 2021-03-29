package com.example.mycinema.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val title: String,
    val description: String,
    val rating: Int
    ) : Parcelable