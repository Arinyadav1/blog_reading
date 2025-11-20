package com.example.blogreading.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class BaseApiManager(
     val retrofit: Retrofit
) {

    private fun <T> createApi(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    fun blogRead() : BlogReadService{
        return createApi(BlogReadService::class.java)
    }

}