package com.example.hotsdraftadviser_kmp.dataclasses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapScoreData(
    @SerialName("MapName")
    val MapName: String,
    @SerialName("ScoreValue")
    val ScoreValue: Int
)