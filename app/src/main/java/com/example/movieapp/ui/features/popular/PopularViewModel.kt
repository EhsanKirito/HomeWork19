package com.example.movieapp.ui.features.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.remote.model.ui.MovieItem
import com.example.movieapp.data.remote.safeapicall.ResponseState
import com.example.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    private val _searchMovies = MutableLiveData<List<MovieItem>>()
    val searchMovies: LiveData<List<MovieItem>> = _searchMovies

    var job:Job? = null
    var page = 1
    var query = ""
    fun searchText() {
        if(job?.isActive == true) {
            Log.e("job", "searchText: job is active" )
            job?.cancel()
        }
        job = viewModelScope.launch {
            delay(700)
            repository.searchMovies(query, page).collect {
                when(it){
                    is ResponseState.Error -> Log.e("TAG", "searchText: ${it.error}" )
                    ResponseState.Loading -> Log.i("loading", "searchText: loading")
                    is ResponseState.Success -> {
                        _searchMovies.postValue(_searchMovies.value?.plus(it.data) ?: it.data)
                    }
                }
            }
        }
    }
    fun nextPage() {
        page++
        searchText()
    }
    fun defaultSearchText(searchQuery:String){
        query = searchQuery
        _searchMovies.postValue(emptyList())
        page = 1
        searchText()
    }
}