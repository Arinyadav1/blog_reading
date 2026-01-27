package com.example.blogreading.data

import androidx.paging.PagingSource
import com.example.blogreading.database.PostDataBase
import com.example.blogreading.database.entity.Post
import com.example.blogreading.database.entity.PostRemoteKey

internal class PostsDatabaseRepositoryImpl(
    private val postDataBase : PostDataBase
) : PostsDatabaseRepository {

    override fun getPost(): PagingSource<Int, Post> =
        postDataBase.postDao.getPost()

    override suspend fun addPost(post: List<Post>) = postDataBase.postDao.addPost(post)

    override suspend fun deleteAllPost() = postDataBase.postDao.deleteAllPost()

    override suspend fun getRemoteKey(id: Int): PostRemoteKey = postDataBase.postRemoteKeyDao.getRemoteKey(id)

    override suspend fun addAllRemoteKey(remoteKey: List<PostRemoteKey>) = postDataBase.postRemoteKeyDao.addAllRemoteKey(remoteKey)

    override suspend fun deleteAllRemoteKey() = postDataBase.postRemoteKeyDao.deleteAllRemoteKey()
}