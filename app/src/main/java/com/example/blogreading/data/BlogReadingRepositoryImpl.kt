package com.example.blogreading.data

import androidx.compose.foundation.pager.PagerState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.blogreading.model.PostResponse
import com.example.blogreading.network.DataManager
import com.example.blogreading.paging.BlogPagingSource
import kotlinx.coroutines.flow.Flow

class BlogReadingRepositoryImpl(
    private val dataManager: DataManager
) : BlogReadingRepository {

    override fun getBlog(): Flow<PagingData<PostResponse>> {
      return Pager(
            config = PagingConfig(
                10
            ),
            pagingSourceFactory = { BlogPagingSource(dataManager)}
        ).flow
    }
}