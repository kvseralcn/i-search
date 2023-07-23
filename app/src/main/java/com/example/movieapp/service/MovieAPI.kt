package com.example.movieapp.service

import com.example.movieapp.model.MovieModel
import io.reactivex.Single
import retrofit2.http.GET

interface MovieAPI {
    @GET("search?term")
    fun getMovies(): Single<List<MovieModel>>
}