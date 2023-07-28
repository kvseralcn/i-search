package com.example.movieapp.core.service

import com.example.movieapp.data.ContentModelDto
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getContent(param: String): Flow<ContentModelDto>
}