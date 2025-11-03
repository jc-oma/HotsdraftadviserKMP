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
import com.example.hotsdraftadviser_kmp.composables.getColorByHexString
import com.example.hotsdraftadviser_kmp.composables.getColorByHexStringForET
import com.example.hotsdraftadviser_kmp.enums.SortState
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
    val mapList by viewModel.filteredMaps.collectAsState(emptyList())
    val choosenMap by viewModel.choosenMap.collectAsState("")
    val chosableChampList by viewModel.chosableChampList.collectAsState(emptyList())
    val sortState by viewModel.sortState.collectAsState(SortState.CHAMPNAME)
    val searchQueryMaps by viewModel.filterMapsString.collectAsState()
    val searchQueryOwnTChamps by viewModel.filterOwnChampString.collectAsState()
    val roleFilter by viewModel.roleFilter.collectAsState()
    val ownPickScore by viewModel.ownPickScore.collectAsState()
    val theirPickScore by viewModel.theirPickScore.collectAsState()

    val targetUIStateByChoosenMap by viewModel.targetState.collectAsState()

    val theirPickedChamps by viewModel.pickedTheirTeamChamps.collectAsState()
    val ownPickedChamps by viewModel.pickedOwnTeamChamps.collectAsState()

    val screenBackgroundColor = "150e35ff"
    val textColor = "f8f8f9ff"
    val headlineColor = "6e35d8ff"
    val theirTeamColor = "5C1A1BFF"
    val ownTeamColor = "533088ff"
    val mapTextColor = "AFEEEEff"
    val composeScreenBackgroundColor = getColorByHexString(screenBackgroundColor)
    val composeTextColor = getColorByHexString(textColor)
    val composeHeadlineColor = getColorByHexString(headlineColor)
    val composeOwnTeamColor = getColorByHexString(ownTeamColor)
    val composeTheirTeamColor = getColorByHexStringForET(theirTeamColor)
    val composeMapTextColor = getColorByHexStringForET(mapTextColor)

    var detectedObjectLabels by remember { mutableStateOf<List<String>>(emptyList()) }

    var targetStateMapName by remember { mutableStateOf<String>("") }

    val isDisclaymerShown by viewModel.isDisclaymerShown.collectAsState()
    val isTutorialShown by viewModel.isTutorialShown.collectAsState()
    val isListMode by viewModel.isListMode.collectAsState()
    val isStarRatingMode by viewModel.isStarRatingMode.collectAsState()

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