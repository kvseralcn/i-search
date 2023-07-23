package com.example.movieapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentResultModel(
    @Json(name = "wrapperType") var wrapperType: String? = null,
    @Json(name = "kind") var kind: String? = null,
    @Json(name = "artistId") var artistId: Int? = null,
    @Json(name = "collectionId") var collectionId: Int? = null,
    @Json(name = "trackId") var trackId: Int? = null,
    @Json(name = "artistName") var artistName: String? = null,
    @Json(name = "collectionName") var collectionName: String? = null,
    @Json(name = "trackName") var trackName: String? = null,
    @Json(name = "collectionCensoredName") var collectionCensoredName: String? = null,
    @Json(name = "trackCensoredName") var trackCensoredName: String? = null,
    @Json(name = "artistViewUrl") var artistViewUrl: String? = null,
    @Json(name = "collectionViewUrl") var collectionViewUrl: String? = null,
    @Json(name = "trackViewUrl") var trackViewUrl: String? = null,
    @Json(name = "previewUrl") var previewUrl: String? = null,
    @Json(name = "artworkUrl30") var artworkUrl30: String? = null,
    @Json(name = "artworkUrl60") var artworkUrl60: String? = null,
    @Json(name = "artworkUrl100") var artworkUrl100: String? = null,
    @Json(name = "collectionPrice") var collectionPrice: Double? = null,
    @Json(name = "trackPrice") var trackPrice: Double? = null,
    @Json(name = "releaseDate") var releaseDate: String? = null,
    @Json(name = "collectionExplicitness") var collectionExplicitness: String? = null,
    @Json(name = "trackExplicitness") var trackExplicitness: String? = null,
    @Json(name = "discCount") var discCount: Int? = null,
    @Json(name = "discNumber") var discNumber: Int? = null,
    @Json(name = "trackCount") var trackCount: Int? = null,
    @Json(name = "trackNumber") var trackNumber: Int? = null,
    @Json(name = "trackTimeMillis") var trackTimeMillis: Int? = null,
    @Json(name = "country") var country: String? = null,
    @Json(name = "currency") var currency: String? = null,
    @Json(name = "primaryGenreName") var primaryGenreName: String? = null,
    @Json(name = "isStreamable") var isStreamable: Boolean? = null
)