package com.example.watcher.models.configuration


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigurationRespons(
    @Json(name = "change_keys")
    val changeKeys: List<String>,
    val images: Images
)