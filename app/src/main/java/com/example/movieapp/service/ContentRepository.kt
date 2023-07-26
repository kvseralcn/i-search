package com.example.movieapp.service

import com.example.movieapp.model.ContentModelDto
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getContent(param: String): Flow<ContentModelDto>
}