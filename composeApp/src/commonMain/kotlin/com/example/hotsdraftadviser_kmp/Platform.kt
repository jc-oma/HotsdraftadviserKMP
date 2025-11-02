package com.example.hotsdraftadviser_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform