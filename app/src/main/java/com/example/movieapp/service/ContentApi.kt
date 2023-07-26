package com.example.movieapp.service

import com.example.movieapp.model.ContentModelDto
import kotlinx.coroutines.flow.Flow

interface ContentApi {
    fun fetchContents(param: String): Flow<ContentModelDto>
}