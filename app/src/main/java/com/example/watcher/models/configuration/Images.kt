package com.example.watcher.models.configuration


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * return possible imageconfigurations
 * @property backdropSizes list of all backdropsizes
 * @property baseUrl baseUrl to create absolute path
 * @property logoSizes list of possible logo sizes
 * @property posterSizes list of possible poster sizes
 * @property profileSizes list of possible profile sizes
 * @property secureBaseUrl baseUrl but with HTTPS
 * @property stillSizes sizes for still images
 */
@JsonClass(generateAdapter = true)
data class Images(
    @Json(name = "backdrop_sizes")
    val backdropSizes: List<String>,
    @Json(name = "base_url")
    val baseUrl: String,
    @Json(name = "logo_sizes")
    val logoSizes: List<String>,
    @Json(name = "poster_sizes")
    val posterSizes: List<String>,
    @Json(name = "profile_sizes")
    val profileSizes: List<String>,
    @Json(name = "secure_base_url")
    val secureBaseUrl: String,
    @Json(name = "still_sizes")
    val stillSizes: List<String>
)