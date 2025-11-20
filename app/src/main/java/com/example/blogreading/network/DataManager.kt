package com.example.blogreading.network

import com.example.blogreading.model.PostResponse

class DataManager(
    val baseApiManager: BaseApiManager
) {
    suspend fun getBlog() : List<PostResponse> {
        return baseApiManager.blogRead().getBlog(
            10,
            1
        )
    }
}