package com.kevser.isearch.core.service

import com.kevser.isearch.data.ContentModelDto
import com.kevser.isearch.data.toDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContentApiImpl @Inject constructor(private val retrofitContentApi: RetrofitContentAPI) :
    ContentApi {

    override fun fetchContents(param: String): Flow<ContentModelDto> = flow {
        emit(retrofitContentApi.fetchContent(param).toDto())
    }
}