package com.example.movieapp.ui.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.model.ui.MovieDetailsItem
import com.example.movieapp.data.remote.safeapicall.ResponseState
import com.example.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MovieRepository, private val state: SavedStateHandle
) : ViewModel() {
    companion object {
        const val RESPONSE_STATE = "responseState"
    }

    private val _movieDetails =
        MutableStateFlow<ResponseState<MovieDetailsItem>>(ResponseState.Loading)
    val movieDetails: StateFlow<ResponseState<MovieDetailsItem>> = _movieDetails
//    val movieDetails2 = _movieDetails.asStateFlow()

    private val movieId = state.get<Int>(DetailsFragment.MOVIE_ID)

    init {
        movieId?.let { getMovieDetails(movieId) }
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieDetails(movieId).collect { responseState ->
                _movieDetails.emit(responseState)

            }
        }
    }

}