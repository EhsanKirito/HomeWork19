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
class DetailsViewModel @Inject constructor(val repository: MovieRepository, private val state:SavedStateHandle):ViewModel(){
    private val _movieDetails = MutableLiveData<MovieDetailsItem>()
    val movieDetails: LiveData<MovieDetailsItem> = _movieDetails
    private val myId = state.get<Int>("movieId")
    init {
        if (myId != null) {
            getMovieDetails(myId)
        }
    }
    private fun getMovieDetails(id:Int){
        viewModelScope.launch {
            repository.getMovieDetails(id).collect{
                when(it){
                    is ResponseState.Error -> Log.e("TAG", "getMovieDetails: nothing" )
                    ResponseState.Loading -> Log.e("TAG", "getMovieDetails: go back" )
                    is ResponseState.Success -> {
                        _movieDetails.postValue(it.data!!)
                    }
                }

            }
        }

    }

}