package com.example.hotsdraftadviser_kmp.composables.champListPortraitItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hotsdraftadviser_kmp.Utilitys
import com.example.hotsdraftadviser_kmp.composables.segmentedButton.SegmentedButtonToOrderChamplistComposable
import com.example.hotsdraftadviser_kmp.dataclasses.ChampData
import com.example.hotsdraftadviser_kmp.dataclasses.exampleChampDataAbathur
import com.example.hotsdraftadviser_kmp.dataclasses.exampleChampDataSgtHammer
import com.example.hotsdraftadviser_kmp.enums.PlatformType
import com.example.hotsdraftadviser_kmp.enums.SortState
import com.example.hotsdraftadviser_kmp.enums.TeamSide
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableChampPortraitComposable(
    sortState: SortState,
    distinctChosableChampList: List<ChampData>,
    distinctAndUnfilteredChosableChampList: List<ChampData>,
    fitTeamMax: Int,
    goodAgainstTeamMax: Int,
    ownScoreMax: Int,
    theirScoreMax: Int,
    choosenMap: String,
    isStarRatingMode: Boolean,
    setSortState: (SortState) -> Unit,
    scrollList: (LazyListState, CoroutineScope) -> Unit,
    toggleFavoriteStatus: (String) -> Unit,
    pickChampForOwnTeam: (Int, TeamSide) -> Unit,
    updateChampSearchQuery: (String) -> Unit,
    setBansPerTeam: (Int, TeamSide) -> Unit,
    platformType: PlatformType,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        SegmentedButtonToOrderChamplistComposable(
            setSortState = { sortState -> setSortState(sortState) },
            sortState = sortState,
            onButtonClick = { scrollList(listState, coroutineScope) }
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(bottom = 180.dp),
            state = listState
        ) {
            items(
                count = distinctChosableChampList.size,
                key = { it -> distinctChosableChampList[it].key }) { i ->

                ChampPortraitItemComposable(
                    champ = distinctChosableChampList[i],
                    toggleChampFavorite = { toggleFavoriteStatus(distinctChosableChampList[i].ChampName) },
                    pickChampForOwnTeam = { pickChampForOwnTeam(i, TeamSide.OWN) },
                    pickChampForTheirTeam = { pickChampForOwnTeam(i, TeamSide.THEIR) },
                    updateChampSearchQuery = { updateChampSearchQuery("") },
                    ownBan = { setBansPerTeam(i, TeamSide.OWN) },
                    theirBan = { setBansPerTeam(i, TeamSide.THEIR) },
                    champDrawable = Utilitys.mapChampNameToDrawable(distinctChosableChampList[i].ChampName)!!,
                    index = i,
                    mapFloat = distinctAndUnfilteredChosableChampList[i].mapFloat,
                    ownTeamFloat = distinctAndUnfilteredChosableChampList[i].fitTeam / fitTeamMax.toFloat(),
                    theirTeamFloat = distinctAndUnfilteredChosableChampList[i].goodAgainstTeam / goodAgainstTeamMax.toFloat(),
                    mapName = choosenMap,
                    maxOwnScore = ownScoreMax,
                    maxTheirScore = theirScoreMax,
                    isStarRating = isStarRatingMode,
                    platformType = platformType
                )
            }
        }
    }
}

@Preview
@Composable
private fun AvailableChampPortraitComposablePreview() {
    AvailableChampPortraitComposable(
        sortState = SortState.OWNPOINTS,
        distinctChosableChampList = listOf(exampleChampDataAbathur, exampleChampDataSgtHammer),
        distinctAndUnfilteredChosableChampList = listOf(exampleChampDataAbathur, exampleChampDataSgtHammer),
        fitTeamMax = 345,
        goodAgainstTeamMax = 123,
        ownScoreMax = 243,
        theirScoreMax = 543,
        choosenMap = "Hanamura",
        isStarRatingMode = true,
        setSortState = {},
        scrollList = { _, _ -> {} },
        toggleFavoriteStatus = {},
        pickChampForOwnTeam = { _, _ -> {} },
        updateChampSearchQuery = { _ -> {} },
        setBansPerTeam = { _, _ -> {} },
        platformType = PlatformType.ANDROID
    )
}