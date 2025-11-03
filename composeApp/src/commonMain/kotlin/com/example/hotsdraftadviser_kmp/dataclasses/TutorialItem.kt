package com.example.hotsdraftadviser_kmp.dataclasses

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource

data class TutorialItem(
    val title: String,
    val description: String,
    val imageResId: DrawableResource?,
    val height: Dp = 200.dp
)