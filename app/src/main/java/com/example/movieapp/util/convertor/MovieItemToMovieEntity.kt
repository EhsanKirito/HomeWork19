package com.example.movieapp.util.convertor

import com.example.movieapp.data.local.Entity.MovieEntity
import com.example.movieapp.data.remote.model.ui.MovieItem

fun MovieItem.movieItemToMovieEntity() = MovieEntity(title, posterPath, id)