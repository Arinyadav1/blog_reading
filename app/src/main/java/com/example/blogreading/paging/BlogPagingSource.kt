package com.example.blogreading.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.blogreading.model.PostResponse
import com.example.blogreading.network.DataManager
import kotlinx.coroutines.flow.first

class BlogPagingSource(
    private val dataManager: DataManager
) : PagingSource<Int, PostResponse>() {

    override fun getRefreshKey(state: PagingState<Int, PostResponse>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostResponse> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response =
                dataManager.getBlog(10, nextPageNumber)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (nextPageNumber == 10) null else nextPageNumber + 1
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }
}