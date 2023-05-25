package com.example.movieapp.di.module

import android.app.Application
import androidx.room.Room
import com.example.movieapp.data.local.database.dao.MovieDao
import com.example.movieapp.data.local.database.MovieDatabase
import com.example.movieapp.data.local.datasource.LocalDataSource
import com.example.movieapp.data.local.datasource.LocalDataSourceImpl
import com.example.movieapp.di.DatabaseName
import com.example.movieapp.util.strings.DataBaseName.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    @DatabaseName
    fun provideDatabaseName(): String = DATABASE_NAME

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application,
        @DatabaseName dataBase: String
    ): MovieDatabase =
        Room.databaseBuilder(application, MovieDatabase::class.java, dataBase)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()


    @Provides
    @Singleton
    fun provideLocalDataSource(movieDao: MovieDao): LocalDataSource = LocalDataSourceImpl(movieDao)
}