package com.example.movieapp.data.remote.model.datatransfer

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieDto>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)