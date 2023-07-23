package com.example.movieapp.model

data class ContentModelDto(
    val resultCount: Int,
    val results: List<ContentResultModel>
)

fun ContentModel.toDto() = ContentModelDto(resultCount, results)