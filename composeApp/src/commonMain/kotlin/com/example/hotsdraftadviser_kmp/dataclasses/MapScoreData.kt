package com.example.hotsdraftadviser_kmp.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class MapScoreData(
    val MapName: String,
    val ScoreValue: Int
)