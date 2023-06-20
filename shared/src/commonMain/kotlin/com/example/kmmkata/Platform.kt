package com.example.kmmkata

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform