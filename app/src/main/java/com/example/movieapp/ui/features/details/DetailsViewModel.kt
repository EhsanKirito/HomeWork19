package com.example.movieapp.ui.features.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.model.ui.MovieDetailsItem
import com.example.movieapp.data.remote.safeapicall.ResponseState
import com.example.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MovieRepository, private val state: SavedStateHandle
) : ViewModel() {
    companion object {
        const val RESPONSE_STATE = "responseState"
    }

    private val _movieDetails = MutableLiveData<MovieDetailsItem>()
    val movieDetails: LiveData<MovieDetailsItem> = _movieDetails
    private val movieId = state.get<Int>(DetailsFragment.MOVIE_ID)

    init {
        movieId?.let { getMovieDetails(movieId) }
    }

    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieDetails(movieId).collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> Log.e(
                        "", "error is: ${responseState.error}"
                    )

                    ResponseState.Loading -> Log.e("responseState", "detailsViewModel : Loading")
                    is ResponseState.Success -> {
                        responseState.data.let { movieDetailsItem ->
                            _movieDetails.postValue(movieDetailsItem)
                        }
                    }
                }
            }
        }
    }

}