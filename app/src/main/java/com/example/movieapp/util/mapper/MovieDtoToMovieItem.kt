package com.example.movieapp.util.mapper

import com.example.movieapp.data.model.datatransfer.MovieDto
import com.example.movieapp.data.model.ui.MovieItem

fun List<MovieDto>?.movieDtoToMovieItem(): List<MovieItem> {
    return this?.map { movieDto ->
        movieDto.movieDtoToMovieItem()
    } ?: emptyList()
}

private fun MovieDto.movieDtoToMovieItem() = MovieItem(title, posterPath, id)