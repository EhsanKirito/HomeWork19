package com.example.movieapp.data.remote.datasource

import com.example.movieapp.data.model.ui.MovieDetailsItem
import com.example.movieapp.data.model.ui.MovieItem
import com.example.movieapp.data.remote.safeapicall.safeApiCall
import com.example.movieapp.data.remote.service.MovieApiService
import com.example.movieapp.util.mapper.movieDetailsResponseToMovieDetailsItem
import com.example.movieapp.util.mapper.movieDtoToMovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class RemoteDataSourceImpl(private val movieService: MovieApiService) : RemoteDataSource {
    override fun getPopularMovies(page: Int): Flow<List<MovieItem>> =
        safeApiCall { movieService.getPopularMovies(page) }.map { it.results!!.movieDtoToMovieItem() }


    override fun getUpcomingMovies(page: Int): Flow<List<MovieItem>> =
        safeApiCall { movieService.getUpcomingMovies(page) }.map { it.results.movieDtoToMovieItem() }


    override fun getMovieDetails(movieId: Int): Flow<MovieDetailsItem> =
        safeApiCall { movieService.getMovieDetails(movieId) }.map { it.movieDetailsResponseToMovieDetailsItem() }

}
//class RemoteDataSourceImpl(private val movieService: MovieApiService) : RemoteDataSource {
//    override fun getPopularMovies(page: Int): Flow<ResponseState<List<MovieItem>>> {
//        return safeApiCallWithMapper({ movieService.getPopularMovies(page)
//        }) {
//            results.movieDtoToMovieItem()
//        }
//    }
//
//    override fun getUpcomingMovies(page: Int): Flow<ResponseState<List<MovieItem>>> {
//        return safeApiCallWithMapper({ movieService.getUpcomingMovies(page) }) {
//            results.movieDtoToMovieItem()
//        }
//    }
//
//    override fun getMovieDetails(movieId: Int): Flow<ResponseState<MovieDetailsItem>> {
//        return safeApiCallWithMapper({ movieService.getMovieDetails(movieId) }) {
//            movieDetailsResponseToMovieDetailsItem()
//        }
//    }
//}