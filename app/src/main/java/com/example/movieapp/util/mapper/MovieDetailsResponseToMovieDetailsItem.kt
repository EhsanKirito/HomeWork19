package com.example.movieapp.util.mapper

import com.example.movieapp.data.model.details.Genre
import com.example.movieapp.data.model.details.MovieDetailsResponse
import com.example.movieapp.data.model.ui.GenreItem
import com.example.movieapp.data.model.ui.MovieDetailsItem


fun MovieDetailsResponse.movieDetailsResponseToMovieDetailsItem(): MovieDetailsItem {
    return MovieDetailsItem(
        genresToGenresItem(genres),
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        backdropPath,
        runtime,
        status,
        voteAverage,
        voteCount
    )
}

private fun genresToGenresItem(genres: List<Genre>?): List<GenreItem> {
    return genres?.map { genre ->
        genre.movieDetailsResponseToMovieDetailsItem()
    } ?: emptyList()
}

private fun Genre.movieDetailsResponseToMovieDetailsItem() = GenreItem(id, name)