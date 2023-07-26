package com.example.movieapp.service

import com.example.movieapp.model.ContentModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitContentAPI {
    @GET("search?")
    suspend fun fetchContent(@Query("term") searchTerm: String): ContentModel
}