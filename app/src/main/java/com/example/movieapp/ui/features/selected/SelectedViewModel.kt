package com.example.movieapp.ui.features.selected

import androidx.lifecycle.ViewModel
import com.example.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectedViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

}