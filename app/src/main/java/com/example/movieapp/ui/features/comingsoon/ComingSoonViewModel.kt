package com.example.movieapp.ui.features.comingsoon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.remote.model.datatransfer.MovieDto
import com.example.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComingSoonViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {


    }