package com.example.movieapp.di.module

import com.example.movieapp.data.remote.datasource.RemoteDataSource
import com.example.movieapp.data.remote.datasource.RemoteDataSourceImpl
import com.example.movieapp.data.remote.service.MovieApiService
import com.example.movieapp.di.ApiKey
import com.example.movieapp.di.BaseUrl
import com.example.movieapp.util.provideApi.provideApi
import com.example.movieapp.util.strings.Services.API_KEY
import com.example.movieapp.util.strings.Services.API_KEY_VALUE
import com.example.movieapp.util.strings.Services.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(): String = API_KEY

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = BASE_URL


    @Provides
    @Singleton
    fun provideInterceptor(@ApiKey apiKey: String): Interceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .addHeader(API_KEY_VALUE, "Bearer $apiKey")
            .build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttpLog(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(interceptor)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        @BaseUrl baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieApiService = provideApi<MovieApiService>(retrofit)

    @Singleton
    @Provides
    fun provideRemoteDataSource(movieService: MovieApiService): RemoteDataSource =
        RemoteDataSourceImpl(movieService)
}