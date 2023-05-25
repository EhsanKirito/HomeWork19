package com.example.movieapp.data.remote.model.details

import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("results")
    val results: List<Video>?
)