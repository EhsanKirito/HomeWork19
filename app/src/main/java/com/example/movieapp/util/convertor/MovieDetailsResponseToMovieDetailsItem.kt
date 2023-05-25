package com.example.movieapp.util.convertor

import com.example.movieapp.data.remote.model.details.Genre
import com.example.movieapp.data.remote.model.details.MovieDetailsResponse
import com.example.movieapp.data.remote.model.ui.GenreItem
import com.example.movieapp.data.remote.model.ui.MovieDetailsItem


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