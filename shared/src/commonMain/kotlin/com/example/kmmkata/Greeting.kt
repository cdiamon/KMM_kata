package com.example.kmmkata

import RocketLaunch
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.call.*
import io.ktor.client.request.*

class Greeting {
    private val platform: Platform = getPlatform()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }

//    fun greet(): String {
//        return "Hello, ${platform.name}!\n" +
//                "Days until new year: ${daysUntilNewYear()}"
//    }

    suspend fun greet(): String {
        val rockets: List<RocketLaunch> =
            httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val successfulLaunch = rockets.last { it.launchSuccess == true }
        return "Guess what it is! > ${platform.name}!" +
                "\nThere are only ${daysUntilNewYear()} left until New Year! 🎆" +
                "\nThe last successful launch was ${successfulLaunch.launchDateUTC} 🚀"
    }
}
