package com.example.hotsdraftadviser_kmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import hotsdraftadviser_kmp.composeapp.generated.resources.Res
import hotsdraftadviser_kmp.composeapp.generated.resources.*

@Composable
@Preview
fun App(
    viewModel: MainViewModel = viewModel(
        factory = mainViewModelFactory,
    )
) {
    MaterialTheme {
        val showContent by viewModel.showContent.collectAsState()
        val champData by viewModel.champData.collectAsState()

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(painterResource(Res.drawable.map_alteracpass_card), null)
            Button(onClick = { viewModel.toggleContent() }) {
                Text("Click me twice!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    if (champData.isNotEmpty()) {
                        Text("Compose: ${champData.get(1).ChampName}")
                    }
                }
            }
        }
    }
}