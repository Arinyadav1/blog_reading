package com.example.blogreading.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.blogreading.database.dao.PostDao
import com.example.blogreading.database.dao.PostRemoteKeyDao
import com.example.blogreading.database.entity.Post
import com.example.blogreading.database.entity.PostRemoteKey

@Database(entities = [Post::class, PostRemoteKey::class], version = 1)
abstract class PostDataBase : RoomDatabase() {

     abstract val postDao : PostDao

     abstract val postRemoteKeyDao : PostRemoteKeyDao
}