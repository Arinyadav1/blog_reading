package com.example.blogreading.network

import com.example.blogreading.model.PostResponse
import kotlinx.coroutines.flow.Flow

class DataManager(
    private val baseApiManager: BaseApiManager
) {
    suspend fun getBlog(
        perPage : Int,
        page : Int
    ) : List<PostResponse>{
        return baseApiManager.blogReadService.getBlog(perPage, page)
    }
}