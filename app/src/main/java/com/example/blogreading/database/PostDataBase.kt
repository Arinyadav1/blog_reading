package com.example.blogreading.database

import androidx.room.RoomDatabase
import com.example.blogreading.database.dao.PostDao
import com.example.blogreading.database.dao.PostRemoteKeyDao

abstract class PostDataBase : RoomDatabase() {

     abstract val postDao : PostDao

     abstract val postRemoteKeyDao : PostRemoteKeyDao
}