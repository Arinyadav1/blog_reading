package com.example.blogreading.network

import de.jensklingenberg.ktorfit.Ktorfit

class BaseApiManager(
   private val ktorfit: Ktorfit
) {
    val blogReadService: BlogReadService = ktorfit.createBlogReadService()
}