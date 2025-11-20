package com.example.blogreading.screen

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModel = module {
    singleOf(::BlogReadingViewModel)
}