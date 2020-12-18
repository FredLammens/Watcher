package com.example.watcher.models.configuration


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * response on how the configuration for images is set.
 * contains the base url and dimensions etc.
 * used ot create the absolute url of an image
 *
 * @property changeKeys reutns a list of all possible properties like air-date , alternative titles etc
 * @property images List of possible image configurations
 */
@JsonClass(generateAdapter = true)
data class ConfigurationRespons(
    @Json(name = "change_keys")
    val changeKeys: List<String>,
    val images: Images
)