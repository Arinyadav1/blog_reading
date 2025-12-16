package com.example.blogreading.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.blogreading.model.PostResponse
import com.example.blogreading.network.DataManager
import com.example.blogreading.data.paging.BlogPagingSource
import kotlinx.coroutines.flow.Flow

internal class PostsRepositoryImpl(
    private val dataManager: DataManager
) : PostsRepository {

    override fun getBlog(): Flow<PagingData<PostResponse>> {
      return Pager(
            config = PagingConfig(
                10
            ),
            pagingSourceFactory = { BlogPagingSource(dataManager)}
        ).flow
    }
}