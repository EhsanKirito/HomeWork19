package com.example.movieapp.data.remote.datasource

import com.example.movieapp.data.model.mapper.movieDetailsResponseToMovieDetailsItem
import com.example.movieapp.data.model.mapper.movieDtoToMovieItem
import com.example.movieapp.data.model.ui.MovieDetailsItem
import com.example.movieapp.data.model.ui.MovieItem
import com.example.movieapp.data.remote.safeapicall.ResponseState
import com.example.movieapp.data.remote.safeapicall.safeApiCallWithMapper
import com.example.movieapp.data.remote.service.MovieApiService
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(private val movieService: MovieApiService) : RemoteDataSource {
    override fun getPopularMovies(page: Int): Flow<ResponseState<List<MovieItem>>> {
        return safeApiCallWithMapper({ movieService.getPopularMovies(page)
        }) {
            results.movieDtoToMovieItem()
        }
    }

    override fun getUpcomingMovies(page: Int): Flow<ResponseState<List<MovieItem>>> {
        return safeApiCallWithMapper({ movieService.getUpcomingMovies(page) }) {
            results.movieDtoToMovieItem()
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<ResponseState<MovieDetailsItem>> {
        return safeApiCallWithMapper({ movieService.getMovieDetails(movieId) }) {
            movieDetailsResponseToMovieDetailsItem()
        }
    }
}