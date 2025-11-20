package com.example.blogreading.network

import com.example.blogreading.model.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("wp-json/wp/v2/posts")
    fun getBlog(
        @Query("per_page") perPage : Int,
        @Query("page") page : Int,
    ) : List<PostResponse>
}