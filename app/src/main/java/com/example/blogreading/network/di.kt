package com.example.blogreading.network

import android.util.Log
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.FlowConverterFactory
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        prettyPrint = true
                    }
                )
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("KtorClient", message)
                    }
                }
            }

            install(DefaultRequest) {
                headers {
                    append("Content-Type", "application/json")
                    append("Accept", "application/json")
                }
            }
        }
    }

    single<Ktorfit> {
        Ktorfit.Builder()
            .baseUrl("https://blog.vrid.in/")
            .httpClient(get<HttpClient>())
            .converterFactories(FlowConverterFactory())
            .build()
    }

    singleOf(::BaseApiManager)
}

val dataManagerModule = module {
    singleOf(::DataManager)
}