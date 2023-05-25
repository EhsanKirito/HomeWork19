package com.example.movieapp.data.local.datasource

import com.example.movieapp.data.DataSource
import com.example.movieapp.data.remote.model.ui.MovieItem
import kotlinx.coroutines.flow.Flow

interface LocalDataSource : DataSource {
    fun getSelectedMovies(): Flow<List<MovieItem>>

    suspend fun insertMovie(movieItem: MovieItem)

    suspend fun deleteMovie(movieItem: MovieItem)
}