package com.example.hotsdraftadviser_kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "hotsdraftadviser_kmp",
    ) {
        App()
    }
}