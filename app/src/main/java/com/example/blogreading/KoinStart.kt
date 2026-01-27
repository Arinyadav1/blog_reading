 package com.example.blogreading

import android.app.Application
import com.example.blogreading.data.repositoryModule
import com.example.blogreading.database.databaseModule
import com.example.blogreading.network.dataManagerModule
import com.example.blogreading.network.networkModule
import com.example.blogreading.feature.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

 class KoinStart : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@KoinStart)
            printLogger(Level.ERROR)
            modules(repositoryModule, networkModule, viewModel, dataManagerModule, databaseModule)
        }
    }
}