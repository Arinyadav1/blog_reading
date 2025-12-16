package com.example.blogreading.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blogreading.database.entity.PostRemoteKey

@Dao
interface PostRemoteKeyDao {

    @Query("SELECT * FROM PostRemoteKey WHERE id = :id")
    suspend fun getRemoteKey(id : Int) : PostRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKey(remoteKey: List<PostRemoteKey>)

    @Query("DELETE FROM PostRemoteKey")
    suspend fun deleteAllRemoteKey()
}