package com.example.blogreading.network

import com.example.blogreading.model.PostResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface BlogReadService {
    @GET("wp-json/wp/v2/posts")
    suspend fun getBlog(
        @Query("per_page") perPage : Int = 50,
        @Query("page") page : Int = 1,
    ) : List<PostResponse>
}