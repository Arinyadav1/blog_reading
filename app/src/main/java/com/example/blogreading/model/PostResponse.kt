package com.example.blogreading.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val id: Int,
    val date: String,
    @SerialName("date_gmt") val dateGmt: String,
    val modified: String,
    @SerialName("modified_gmt") val modifiedGmt: String,
    val slug: String,
    val status: String,
    val type: String,
    val link: String,
    val title: RenderedText,
    val author: Int,
    @SerialName("comment_status") val commentStatus: String,
    @SerialName("ping_status") val pingStatus: String,
    val format: String,
    @SerialName("jetpack_featured_media_url") val featuredMediaUrl: String,
)

@Serializable
data class RenderedText(
    val rendered: String
)

