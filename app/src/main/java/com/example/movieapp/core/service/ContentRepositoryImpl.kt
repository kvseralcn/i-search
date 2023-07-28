package com.example.movieapp.core.service

import com.example.movieapp.data.ContentModelDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentAPI: ContentApi
) : ContentRepository {

    override fun getContent(param: String): Flow<ContentModelDto> =
        contentAPI.fetchContents(param)
            .flowOn(Dispatchers.IO)
}