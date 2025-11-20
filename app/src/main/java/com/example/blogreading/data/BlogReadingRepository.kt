package com.example.blogreading.data

import com.example.blogreading.model.PostResponse

interface BlogReadingRepository {

    suspend fun getBlog() : List<PostResponse>
}