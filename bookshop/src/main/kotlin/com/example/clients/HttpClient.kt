package com.example.clients

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

val applicationHttpClient = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json()
    }
}
