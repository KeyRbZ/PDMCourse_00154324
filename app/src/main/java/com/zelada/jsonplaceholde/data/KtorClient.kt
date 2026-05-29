package com.zelada.jsonplaceholde.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Singleton del cliente Ktor.
 * - Motor: OkHttp (más estable en Android que el engine CIO)
 * - ContentNegotiation con ignoreUnknownKeys = true
 * - Logging con LogLevel.ALL para depurar en Logcat durante el examen
 */
object KtorClient {
    val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true   // Evita crash con campos extra de la API
                isLenient = true
                encodeDefaults = true
            })
        }
        install(Logging) {
            level = LogLevel.ALL           // Ver peticiones/respuestas completas en Logcat
        }
    }
}

