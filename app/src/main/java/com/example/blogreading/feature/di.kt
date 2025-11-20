package com.example.blogreading.feature

import com.example.blogreading.feature.blogList.BlogListViewModel
import com.example.blogreading.feature.webView.WebViewViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModel = module {
    singleOf(::BlogListViewModel)
    singleOf(::WebViewViewModel)
}