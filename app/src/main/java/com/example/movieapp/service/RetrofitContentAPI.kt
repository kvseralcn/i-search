package com.example.movieapp.service

import com.example.movieapp.model.ContentModel
import retrofit2.http.GET

interface RetrofitContentAPI {
    // @GET("search?term")
    @GET("search?term=jack+johnson")
    suspend fun fetchContent(): ContentModel
}