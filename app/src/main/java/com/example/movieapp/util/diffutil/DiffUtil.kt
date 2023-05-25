package com.example.movieapp.util.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.data.remote.model.ui.MovieItem

class MovieCallBack : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return oldItem.id == newItem.id
    }
}