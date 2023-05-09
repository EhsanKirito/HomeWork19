package com.example.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.model.local.Entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}