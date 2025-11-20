package com.example.blogreading.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class NetworkBuilder() {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://blog.vrid.in/")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}