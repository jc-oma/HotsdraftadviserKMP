
package com.example.hotsdraftadviser_kmp.composables.champListPortraitItem

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hotsdraftadviser_kmp.Utilitys
import com.example.hotsdraftadviser_kmp.composables.segmentedButton.SegmentedButtonToOrderChamplistComposable
import com.example.hotsdraftadviser_kmp.composables.simpleVerticalScrollbar
import com.example.hotsdraftadviser_kmp.dataclasses.ChampData
import com.example.hotsdraftadviser_kmp.dataclasses.exampleChampDataAbathur
import com.example.hotsdraftadviser_kmp.dataclasses.exampleChampDataSgtHammer
import com.example.hotsdraftadviser_kmp.enums.PlatformType
import com.example.hotsdraftadviser_kmp.enums.SortState
import com.example.hotsdraftadviser_kmp.enums.TeamSide
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AvailableWebChampListComposable (sortState: SortState,
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
                                       platformType: PlatformType) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val state = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()
        .padding( start = 160.dp, end = 160.dp)) {
        SegmentedButtonToOrderChamplistComposable(
            setSortState = { sortState -> setSortState(sortState) },
            sortState = sortState,
            onButtonClick = { scrollList(listState, coroutineScope) }
        )
        LazyVerticalGrid(
            modifier = Modifier.background(Color.Transparent)
                .simpleVerticalScrollbar(listState),
            contentPadding = PaddingValues(bottom = 160.dp, top = 32.dp),
            columns = GridCells.Adaptive(
                minSize = 380.dp
            )
        ) {
            items(
                count = distinctChosableChampList.size,
                key = { it -> distinctChosableChampList[it].key }) { i ->
                //TODO hier drunter manchmal out of bounds Exception
                val currentChamp = distinctChosableChampList[i]
                val currentChampUnfilt = distinctAndUnfilteredChosableChampList[i]

                ChampPortraitItemComposable(
                    modifier = Modifier.animateItem(fadeInSpec = tween<Float>(
                        durationMillis = 500,
                        delayMillis = 200,
                        easing = LinearOutSlowInEasing
                    )),
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
private fun AvailableWebChampListComposablePreview() {
    AvailableWebChampListComposable(
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