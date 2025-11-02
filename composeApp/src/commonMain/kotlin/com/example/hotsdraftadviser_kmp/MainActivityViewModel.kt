package com.example.hotsdraftadviser_kmp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainActivityViewModel {
    private val _showContent = MutableStateFlow(true)
    val showContent: StateFlow<Boolean> = _showContent.asStateFlow()

    fun toggleContent() {
        _showContent.update { !it }
    }
}
