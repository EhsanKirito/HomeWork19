package com.example.movieapp.data.repository

import com.example.movieapp.data.local.datasource.LocalDataSource
import com.example.movieapp.data.remote.model.ui.MovieDetailsItem
import com.example.movieapp.data.remote.model.ui.MovieItem
import com.example.movieapp.data.remote.datasource.RemoteDataSource
import com.example.movieapp.data.remote.safeapicall.ResponseState
import com.example.movieapp.data.remote.safeapicall.asResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun getPopularMovies(page: Int): Flow<ResponseState<List<MovieItem>>> = remoteDataSource.getPopularMovies(page).asResponseState()

    fun getUpcomingMovies(page: Int): Flow<ResponseState<List<MovieItem>>> = remoteDataSource.getUpcomingMovies(page).asResponseState()

    fun getMovieDetails(movieId: Int): Flow<ResponseState<MovieDetailsItem>> = remoteDataSource.getMovieDetails(movieId).asResponseState()

    fun getSeSelectedMovies(): Flow<List<MovieItem>> = localDataSource.getSelectedMovies()

    suspend fun insertMovie(movie: MovieItem) {
        localDataSource.insertMovie(movie)
    }

    suspend fun deleteMovie(movie: MovieItem) {
        localDataSource.deleteMovie(movie)
    }

    fun searchMovies(searchText:String, page:Int): Flow<ResponseState<List<MovieItem>>> {
        return remoteDataSource.searchMovies(searchText, page).asResponseState()
    }
}