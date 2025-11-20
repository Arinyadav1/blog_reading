package com.example.blogreading.data

import com.example.blogreading.model.PostResponse
import com.example.blogreading.network.ApiService
import com.example.blogreading.network.NetworkBuilder

class BlogReadingRepositoryImpl(
    private val networkBuilder: NetworkBuilder
) : BlogReadingRepository {

    override suspend fun getBlog(): List<PostResponse> {
        return networkBuilder.apiService.getBlog(
            perPage = 10,
            page = 1,
        )
    }
}