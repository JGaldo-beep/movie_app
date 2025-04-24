package com.example.movieapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    @StringRes val title: Int,
    @DrawableRes val imageResId: Int,
    @StringRes val description: Int,
    @StringRes val genre: Int,
    @StringRes val year: Int
)
