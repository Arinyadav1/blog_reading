package com.example.blogreading.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Post")
data class Post(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val date: String,
    val link: String,
    val title: String,
    val featuredMediaUrl: String,
)
