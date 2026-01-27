package com.example.blogreading.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PostRemoteKey")
data class PostRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
)
