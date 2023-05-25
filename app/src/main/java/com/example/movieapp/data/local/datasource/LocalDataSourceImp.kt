package com.example.movieapp.data.local.datasource

import com.example.movieapp.data.local.database.dao.MovieDao
import com.example.movieapp.util.convertor.movieEntityToMovieItem
import com.example.movieapp.util.convertor.movieItemToMovieEntity
import com.example.movieapp.data.remote.model.ui.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(private val movieDao: MovieDao) : LocalDataSource {
    override fun getSelectedMovies(): Flow<List<MovieItem>> = flow {
        movieDao.getSelectedMovies().collect { moviesEntity ->
            emit(moviesEntity.movieEntityToMovieItem())
        }
    }
    override suspend fun insertMovie(movieItem: MovieItem) {
        movieDao.insertMovie(movieItem.movieItemToMovieEntity())
    }

    override suspend fun deleteMovie(movieItem: MovieItem) {
        movieDao.deleteMovie(movieItem.movieItemToMovieEntity())
    }
}