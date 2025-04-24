package com.example.movieapp.data

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.movieapp.R

object Resource {
    val allMovies: List<Movie> = listOf(
        Movie(
            title = R.string.name1,
            description = R.string.description1,
            imageResId = R.drawable.movie_1,
            genre = R.string.genre1,
            year = R.string.year1
        ),
        Movie(
            title = R.string.name2,
            description = R.string.description2,
            imageResId = R.drawable.movie_2,
            genre = R.string.genre2,
            year = R.string.year2
        ),
        Movie(
            title = R.string.name3,
            description = R.string.description3,
            imageResId = R.drawable.movie_3,
            genre = R.string.genre3,
            year = R.string.year3
        ),
        Movie(
            title = R.string.name4,
            description = R.string.description4,
            imageResId = R.drawable.movie_4,
            genre = R.string.genre4,
            year = R.string.year4
        ),
        Movie(
            title = R.string.name5,
            description = R.string.description5,
            imageResId = R.drawable.movie_5,
            genre = R.string.genre5,
            year = R.string.year5
        ),
        Movie(
            title = R.string.name6,
            description = R.string.description6,
            imageResId = R.drawable.movie_6,
            genre = R.string.genre6,
            year = R.string.year6
        ),
        Movie(
            title = R.string.name7,
            description = R.string.description7,
            imageResId = R.drawable.movie_7,
            genre = R.string.genre7,
            year = R.string.year7
        ),
    )
}