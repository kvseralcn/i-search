package com.example.movieapp.core.service

import com.example.movieapp.data.ContentModelDto
import com.example.movieapp.data.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContentApiImpl @Inject constructor(private val retrofitContentApi: RetrofitContentAPI) :
    ContentApi {

    override fun fetchContents(param: String): Flow<ContentModelDto> = flow {
        emit(retrofitContentApi.fetchContent(param).toDto())
    }
}