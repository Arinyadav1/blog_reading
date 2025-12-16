package com.example.blogreading.data

import androidx.paging.PagingData
import com.example.blogreading.model.PostResponse
import kotlinx.coroutines.flow.Flow

interface  PostsRepository {
    fun getBlog() : Flow<PagingData<PostResponse>>
}