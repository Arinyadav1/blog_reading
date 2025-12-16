package com.example.blogreading.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blogreading.database.entity.Post
import de.jensklingenberg.ktorfit.http.POST

@Dao
interface PostDao {

    @Query("SELECT * FROM Post")
    fun getPost() : PagingSource<Int, Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPost(post : List<Post>)

    @Query("DELETE FROM Post")
    suspend fun deleteAllPost()
}