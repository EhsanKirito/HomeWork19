package com.example.movieapp.util.convertor

import com.example.movieapp.data.local.Entity.MovieEntity
import com.example.movieapp.data.remote.model.ui.MovieItem

fun List<MovieEntity>.movieEntityToMovieItem() = this.map { movieEntity ->
    movieEntity.movieEntityToMovieItem()
}

private fun MovieEntity.movieEntityToMovieItem() = MovieItem(title, posterPath, id)