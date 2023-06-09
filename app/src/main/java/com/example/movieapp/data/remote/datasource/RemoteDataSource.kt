package com.example.movieapp.data.remote.datasource

import com.example.movieapp.data.remote.model.ui.MovieDetailsItem
import com.example.movieapp.data.remote.model.ui.MovieItem
import kotlinx.coroutines.flow.Flow


interface RemoteDataSource {
    fun getPopularMovies(page: Int): Flow<List<MovieItem>>
    fun getUpcomingMovies(page: Int): Flow<List<MovieItem>>
    fun getMovieDetails(movieId: Int): Flow<MovieDetailsItem>
    fun searchMovies(searchText:String, page:Int):Flow<List<MovieItem>>
}
