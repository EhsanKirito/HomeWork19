package com.example.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.local.Entity.MovieEntity
import com.example.movieapp.data.local.database.dao.MovieDao

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}