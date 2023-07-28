package com.example.movieapp.core.service

import com.example.movieapp.data.ContentModelDto
import kotlinx.coroutines.flow.Flow

interface ContentApi {
    fun fetchContents(param: String): Flow<ContentModelDto>
}