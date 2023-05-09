package com.example.movieapp.util.mapper

import com.example.movieapp.data.model.local.Entity.MovieEntity
import com.example.movieapp.data.model.ui.MovieItem

fun List<MovieEntity>.movieEntityToMovieItem() = this.map { movieEntity ->
    movieEntity.movieEntityToMovieItem()
}

private fun MovieEntity.movieEntityToMovieItem() = MovieItem(title, posterPath, id)