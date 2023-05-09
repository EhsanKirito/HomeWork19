package com.example.movieapp.util.mapper

import com.example.movieapp.data.model.local.Entity.MovieEntity
import com.example.movieapp.data.model.ui.MovieItem

fun MovieItem.movieItemToMovieEntity() = MovieEntity(title, posterPath, id)