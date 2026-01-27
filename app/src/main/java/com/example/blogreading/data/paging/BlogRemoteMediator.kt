package com.example.blogreading.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.blogreading.data.PostsDatabaseRepository
import com.example.blogreading.database.entity.Post
import com.example.blogreading.database.entity.PostRemoteKey
import com.example.blogreading.network.DataManager

@OptIn(ExperimentalPagingApi::class)
class BlogRemoteMediator(
    private val dataBase: PostsDatabaseRepository,
    private val dataManager: DataManager,
) : RemoteMediator<Int, Post>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Post>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKey?.prevPage
                        ?: return MediatorResult.Success(
                            true
                        )

                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(
                            true
                        )
                    nextPage
                }
            }

            val response = dataManager.getBlog(10, currentPage)

            val endOfPaginationReached = 10 == currentPage

            val prevKey = if (currentPage == 1) null else currentPage - 1
            val nextKey = if (endOfPaginationReached) null else currentPage + 1

            if(loadType == LoadType.REFRESH){
                dataBase.deleteAllPost()
                dataBase.deleteAllRemoteKey()
            }

            val post: List<Post> = response.map {
                Post(
                    id = it.id,
                    date = it.date,
                    link = it.link,
                    title = it.title.rendered,
                    featuredMediaUrl = it.featuredMediaUrl
                )
            }

            val key = response.map {
                PostRemoteKey(
                    id = it.id,
                    nextPage = nextKey,
                    prevPage = prevKey
                )
            }

            dataBase.addPost(post)
            dataBase.addAllRemoteKey(key)

            MediatorResult.Success(endOfPaginationReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Post>
    ): PostRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let {
            dataBase.getRemoteKey(id = it.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Post>
    ): PostRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let {
            dataBase.getRemoteKey(id = it.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Post>
    ): PostRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                dataBase.getRemoteKey(id = it)
            }
        }
    }

}