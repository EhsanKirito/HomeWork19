package com.example.movieapp.ui.pages.comingsoon

import androidx.lifecycle.ViewModel
import com.example.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComingSoonViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    }