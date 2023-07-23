package com.example.movieapp.service

import com.example.movieapp.model.ContentModelDto
import com.example.movieapp.model.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContentApiImpl @Inject constructor(private val retrofitContentApi: RetrofitContentAPI) :
    ContentApi {

    override fun fetchContents(): Flow<ContentModelDto> = flow {
        emit(retrofitContentApi.fetchContent().toDto())
    }
}