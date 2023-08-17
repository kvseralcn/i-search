package com.kevser.isearch.extension

import com.kevser.isearch.data.ContentResultModel

fun List<ContentResultModel>.extractUniqueGenreNames(): List<String> {
    val uniqueGenreNames = mutableSetOf<String>()

    this.forEach { contentResultModel ->
        contentResultModel.primaryGenreName?.let { uniqueGenreNames.add(it) }
    }

    return uniqueGenreNames.toList()
}