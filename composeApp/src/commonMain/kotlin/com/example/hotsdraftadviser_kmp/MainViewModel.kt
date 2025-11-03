package com.example.hotsdraftadviser_kmp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.hotsdraftadviser_kmp.dataclasses.ChampData
import hotsdraftadviser_kmp.composeapp.generated.resources.Res
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.collections.emptyList

class MainViewModel() : ViewModel() {
    private val _showContent = MutableStateFlow(true)
    private val _champData = MutableStateFlow<List<ChampData>>(emptyList())
    val showContent: StateFlow<Boolean> = _showContent.asStateFlow()
    val champData: StateFlow<List<ChampData>> = _champData.asStateFlow()


    init {
        loadJson()
    }

    private fun loadJson() {
        viewModelScope.launch(Dispatchers.Default) {
            val readBytes = Res.readBytes("files/output.json")
            val jsonString = readBytes.decodeToString()
            val champData = Json.decodeFromString<List<ChampData>>(jsonString)
            _champData.value = champData
        }
    }

    fun toggleContent() {
        _showContent.update { !it }
    }
}

// ViewModelFactory that retrieves the data repository for your app.
val mainViewModelFactory = viewModelFactory {
    initializer {
        MainViewModel()
    }
}

// siehe: https://developer.android.com/kotlin/multiplatform/viewmodel?hl=de
//fun getDataRepository(): DataRepository = DataRepository()