package com.kevser.isearch.core.service

import com.kevser.isearch.data.ContentModelDto
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getContent(param: String): Flow<ContentModelDto>
}