package com.example.blogreading.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.blogreading.model.PostResponse
import com.example.blogreading.network.DataManager
import com.example.blogreading.data.paging.BlogPagingSource
import com.example.blogreading.data.paging.BlogRemoteMediator
import com.example.blogreading.database.entity.Post
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
internal class PostsRepositoryImpl(
    private val dataManager: DataManager,
    private val repository: PostsDatabaseRepository
) : PostsRepository {

    override fun getBlog(): Flow<PagingData<Post>> {
      return Pager(
            config = PagingConfig(
                20,
                maxSize = 100
            ),
            remoteMediator = BlogRemoteMediator(repository, dataManager),
            pagingSourceFactory = { repository.getPost() }
        ).flow
    }
}