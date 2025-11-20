package com.example.blogreading.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single {
        Json { ignoreUnknownKeys = true }
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/")
            .addConverterFactory(
                get<Json>().asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    singleOf(::BaseApiManager)
}

val dataManagerModule = module {
    singleOf(::DataManager)
}