package com.example.hotsdraftadviser_kmp.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class GoodTeamWith(
    val ChampName: String,
    val ScoreValue: Int
)