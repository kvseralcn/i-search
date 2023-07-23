package com.example.movieapp.service

import com.example.movieapp.model.MovieModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPIService {
    private val baseUrl = "https://itunes.apple.com/"
    private val api = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getData(): Single<List<MovieModel>> {
        return api.getMovies()
    }
}