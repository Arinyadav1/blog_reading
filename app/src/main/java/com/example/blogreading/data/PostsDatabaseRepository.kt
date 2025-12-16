package com.example.blogreading.data

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blogreading.database.entity.Post
import com.example.blogreading.database.entity.PostRemoteKey

interface PostsDatabaseRepository {

    fun getPost() : PagingSource<Int, Post>

    suspend fun addPost(post : List<Post>)

    suspend fun deleteAllPost()

    suspend fun getRemoteKey(id : Int) : PostRemoteKey

    suspend fun addAllRemoteKey(remoteKey: List<PostRemoteKey>)

    suspend fun deleteAllRemoteKey()
}