package com.example.movieapp.data.model.mapper

import com.example.movieapp.data.model.Entity.MovieEntity
import com.example.movieapp.data.model.ui.MovieItem

fun MovieItem.movieItemToMovieEntity() = MovieEntity(title, posterPath, id)