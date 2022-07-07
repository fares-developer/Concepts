package com.example.learning.data.model

import com.squareup.moshi.Json

data class Poster(
    @Json(name = "id")
    val id: String,
    @Json(name = "alt_description")
    val altDescription: String? = "",
    @Json(name = "liked_by_user")
    val likedByUser: Boolean,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "urls")
    val urls: Urls,
    @Json(name = "user")
    val user: User,
)