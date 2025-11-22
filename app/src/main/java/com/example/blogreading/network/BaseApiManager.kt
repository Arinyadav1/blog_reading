package com.example.blogreading.network

import retrofit2.Retrofit

class BaseApiManager(
    retrofit: Retrofit
) {
    val blogReadService: BlogReadService = retrofit.create(BlogReadService::class.java)
}