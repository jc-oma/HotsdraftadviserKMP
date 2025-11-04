package com.example.hotsdraftadviser_kmp.composables.menus

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FloatingActionButtonMainActivity(
    resetCount: Int,
    resetSelections: () -> Unit
) {
    val freeResetCount = 5
    val adPeriod = 3

    //TODO
    //MainWindowAdInterstitial { showAd ->
    FloatingActionButton(
        onClick = {
            resetSelections()
            if (resetCount > freeResetCount && resetCount % adPeriod == 0) {
                //Toast.makeText(context, "Showing ad", Toast.LENGTH_SHORT).show()
                //showAd()
            }
        }
    ) {
        Icon(Icons.Filled.Refresh, "Reset selections")
    }
}


@Preview(showBackground = true)
@Composable
private fun FloatingActionButtonMainActivityPreview() {
    FloatingActionButtonMainActivity(
        resetSelections = {},
        resetCount = 0
    )
}