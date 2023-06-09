package com.example.movieapp.data.remote.model.ui

data class MovieDetailsItem(
    val genres: List<GenreItem>?,
    val id: Int?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val runtime: Int?,
    val status: String?,
    val voteAverage: Float?,
    val voteCount: Int?
)