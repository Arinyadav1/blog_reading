package com.example.blogreading.network

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    singleOf(::NetworkBuilder)
}