package com.example.movieapp.core.service

import com.example.movieapp.data.ContentModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitContentAPI {
    @GET("search?")
    suspend fun fetchContent(@Query("term") searchTerm: String): ContentModel
}