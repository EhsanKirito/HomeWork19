package com.example.movieapp.util.convertor

import com.example.movieapp.data.remote.model.ui.MovieDetailsItem

fun MovieDetailsItem.toMovieDetailsItemFragment() =
    MovieDetailsFragment(
        originalTitle ?: "بدون اسم(این اسم فیلم نیست)",
        releaseDate ?: "0000/00/00",
        (voteAverage ?: 0.0f) / 2
    )

data class MovieDetailsFragment(val title: String, val releaseDate: String, val voteAverage: Float)