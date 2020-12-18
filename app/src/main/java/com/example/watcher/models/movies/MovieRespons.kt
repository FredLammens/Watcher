package com.example.watcher.models.movies


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents a movieresponse
 * @property page amount of pages
 * @property results movieObjects
 * @property totalPages how many pages there are in the database of this collection
 * @property totalResults how many movie objects there are in the database of this collection
 */
@JsonClass(generateAdapter = true)
data class MovieRespons(
    val page: Int,
    val results: List<Result>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)