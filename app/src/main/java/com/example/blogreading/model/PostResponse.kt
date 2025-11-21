package com.example.blogreading.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val id: Int,
    val date: String,
    val link: String,
    val title: RenderedText,
    @SerialName("jetpack_featured_media_url") val featuredMediaUrl: String,
)

@Serializable
data class RenderedText(
    val rendered: String
)

