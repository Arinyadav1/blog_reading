package com.example.blogreading.data

import com.example.blogreading.model.PostResponse
import com.example.blogreading.network.DataManager

class BlogReadingRepositoryImpl(
    private val dataManager: DataManager
) : BlogReadingRepository {

    override suspend fun getBlog(): List<PostResponse> {
        return dataManager.getBlog()
    }
}