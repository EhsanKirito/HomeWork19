package com.example.movieapp.data.model.local.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MovieEntity(
    @ColumnInfo("movie_title") val title: String?,
    @ColumnInfo("movie_poster") val posterPath: String?,
    @PrimaryKey(autoGenerate = true) @ColumnInfo("movie_id") val id: Int?
)