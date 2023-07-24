package com.example.movieapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentModel(
    @Json(name = "resultCount") val resultCount: Int,
    @Json(name = "results") val results: List<ContentResultModel>
)