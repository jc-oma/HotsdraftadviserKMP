package com.example.hotsdraftadviser_kmp.dataclasses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeakAgainstData(
    @SerialName("ChampName")
    val ChampName: String,
    @SerialName("ScoreValue")
    val ScoreValue: Int
)
