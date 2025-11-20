package com.example.blogreading

import android.app.Application
import com.example.blogreading.data.repositoryModule
import com.example.blogreading.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class KoinStart : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@KoinStart)
            modules(repositoryModule, networkModule)
        }
    }
}