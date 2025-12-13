package com.example.blogreading.data

import com.example.blogreading.paging.BlogPagingSource
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(
        ::BlogReadingRepositoryImpl
    ) { bind<BlogReadingRepository>() }

    singleOf(::BlogPagingSource)
}