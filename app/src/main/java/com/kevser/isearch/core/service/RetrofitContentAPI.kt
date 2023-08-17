package com.kevser.isearch.core.service

import com.kevser.isearch.data.ContentModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitContentAPI {
    @GET("search?")
    suspend fun fetchContent(@Query("term") searchTerm: String): ContentModel
}