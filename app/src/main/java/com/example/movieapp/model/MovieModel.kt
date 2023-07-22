package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("resultCount") var resultCount: Int? = null,
    @SerializedName("results") var results: ArrayList<MovieResultModel> = arrayListOf()
)