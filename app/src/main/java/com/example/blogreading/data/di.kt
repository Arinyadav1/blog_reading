package com.example.blogreading.data

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(
        ::BlogReadingRepositoryImpl
    ) { bind<BlogReadingRepository>() }
}