package com.example.movieapp.data.model.mapper

import com.example.movieapp.data.model.Entity.MovieEntity
import com.example.movieapp.data.model.ui.MovieItem

fun List<MovieEntity>.movieEntityToMovieItem() = this.map { movieEntity ->
    movieEntity.movieEntityToMovieItem()
}

private fun MovieEntity.movieEntityToMovieItem() = MovieItem(title, posterPath, id)