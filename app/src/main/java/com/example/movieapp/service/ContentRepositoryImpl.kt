package com.example.movieapp.service

import com.example.movieapp.model.ContentModelDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentAPI: ContentApi
) : ContentRepository {

    override fun getContent(): Flow<ContentModelDto> =
        contentAPI.fetchContents()
            .flowOn(Dispatchers.IO)
}