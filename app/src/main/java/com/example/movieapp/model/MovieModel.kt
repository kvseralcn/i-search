package com.example.movieapp.model

import com.squareup.moshi.Json

data class MovieModel(
    @Json(name = "resultCount") var resultCount: Int? = null,
    @Json(name = "results") var results: ArrayList<MovieResultModel> = arrayListOf()
)