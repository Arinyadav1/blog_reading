package com.example.blogreading.database

import androidx.room.Database
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val databaseModule = module{

    single{
        Room.databaseBuilder(
                androidContext(),
                PostDataBase::class.java,
                "post_database"
            ).fallbackToDestructiveMigration(true)
            .build()
    }

}