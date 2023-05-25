package com.example.movieapp.util.convertor

import com.example.movieapp.data.remote.model.datatransfer.MovieDto
import com.example.movieapp.data.remote.model.ui.MovieItem

fun List<MovieDto>?.movieDtoToMovieItem(): List<MovieItem> {
    return this?.map { movieDto ->
        movieDto.movieDtoToMovieItem()
    } ?: emptyList()
}
private fun MovieDto.movieDtoToMovieItem() = MovieItem(title, posterPath, id)
