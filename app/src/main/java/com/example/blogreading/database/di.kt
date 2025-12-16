package com.example.blogreading.database

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module{

    single<PostDataBase>{
        Room.databaseBuilder(
            androidApplication().applicationContext,
            PostDataBase::class.java,
            "Post DB"
        ).build()
    }
}