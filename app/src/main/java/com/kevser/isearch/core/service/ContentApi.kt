package com.kevser.isearch.core.service

import com.kevser.isearch.data.ContentModelDto
import kotlinx.coroutines.flow.Flow

interface ContentApi {
    fun fetchContents(param: String): Flow<ContentModelDto>
}