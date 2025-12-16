package com.example.blogreading.data.paging

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
        /**
         * The purpose of getRefreshKey():
         *
         * When Paging refreshes (e.g. configuration change, process death, swipe refresh),
         * it needs to know **which page to reload** so that the user remains close to the
         * same scroll position instead of jumping back to the top.
         *
         * PagingState provides information about:
         * - Currently loaded pages
         * - The user's most recently accessed item index (anchorPosition)
         *
         * ---------------------------------------------------------------
         * Example (page size = 10):
         *
         * Page 1 → items 0  - 9
         * Page 2 → items 10 - 19
         * Page 3 → items 20 - 29
         *
         * If the user is viewing item 15 on screen:
         * - anchorPosition = 15
         * - The user is currently on Page 2
         * ---------------------------------------------------------------
         */

        return state.anchorPosition?.let { anchorPosition ->

            /**
             * Find the page that is closest to the user's current scroll position.
             *
             * For anchorPosition = 15:
             * closestPageToPosition(15) → Page 2
             *
             * Page 2 contains:
             * - prevKey = 1
             * - nextKey = 3
             */
            val closestPage = state.closestPageToPosition(anchorPosition)

            /**
             * Determine the refresh key:
             *
             * - If prevKey exists, the current page is (prevKey + 1)
             * - Otherwise, if nextKey exists, the current page is (nextKey - 1)
             *
             * This calculation ensures that Paging refreshes starting from
             * the page the user was currently viewing.
             */
            closestPage?.prevKey?.plus(1)
                ?: closestPage?.nextKey?.minus(1)
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