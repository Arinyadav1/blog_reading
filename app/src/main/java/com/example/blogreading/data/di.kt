package com.example.blogreading.data

import com.example.blogreading.data.paging.BlogPagingSource
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(
        ::PostsRepositoryImpl
    ) { bind<PostsRepository>() }

    singleOf(::PostsDatabaseRepositoryImpl) { bind<PostsDatabaseRepository>() }

    singleOf(::BlogPagingSource)
}