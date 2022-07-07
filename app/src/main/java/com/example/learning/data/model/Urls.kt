package com.example.learning.data.model

import com.squareup.moshi.Json

data class Urls(
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    @Json(name = "small_s3") val smallS3: String,
    val thumb: String
)