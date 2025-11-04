package com.example.hotsdraftadviser_kmp

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hotsdraftadviser_kmp.Utilitys.getPlatformType
import com.example.hotsdraftadviser_kmp.composables.MenuComposable
import com.example.hotsdraftadviser_kmp.composables.champListPortraitItem.AvailableChampListComposable
import com.example.hotsdraftadviser_kmp.composables.champListPortraitItem.AvailableChampPortraitComposable
import com.example.hotsdraftadviser_kmp.composables.getColorByHexString
import com.example.hotsdraftadviser_kmp.composables.getColorByHexStringForET
import com.example.hotsdraftadviser_kmp.composables.menus.DisclaimerComposable
import com.example.hotsdraftadviser_kmp.composables.menus.FloatingActionButtonMainActivity
import com.example.hotsdraftadviser_kmp.composables.menus.tutorial.TutorialCarouselComposable
import com.example.hotsdraftadviser_kmp.composables.searchbar.MapSearchBar
import com.example.hotsdraftadviser_kmp.enums.PlatformType
import com.example.hotsdraftadviser_kmp.enums.SortState
import com.example.hotsdraftadviser_kmp.filter.SearchAndFilterRowForChampsSmall
import com.example.hotsdraftadviser_kmp.pickedChamps.ListOfPickedChampsComposable
import hotsdraftadviser_kmp.composeapp.generated.resources.Res
import hotsdraftadviser_kmp.composeapp.generated.resources.app_Background
import hotsdraftadviser_kmp.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalSharedTransitionApi::class)
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

    val showContent by viewModel.showContent.collectAsState()
    val champData by viewModel.champData.collectAsState()

    val platformType = remember { getPlatformType() }
    val modifier = if (platformType == PlatformType.WEB) {
        val webPadding = 160
        Modifier.padding(start = webPadding.dp, end = webPadding.dp)
    } else {
        Modifier
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButtonMainActivity(
                resetSelections = {
                    viewModel.resetAll()
                    viewModel.incrementResetCounter()
                },
                //TODO
                resetCount = 0
            )
        }
    ) { innerPadding ->
        MaterialTheme {
            //Text(stringResource( Res.string.app_name))
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(
                    resource = Res.drawable.app_Background,
                ),
                contentDescription = "background"
            )
            SharedTransitionLayout {
                AnimatedContent(
                    modifier = modifier,
                    targetState = targetUIStateByChoosenMap
                ) { targetState ->
                    val animatedVisibilityScope = this@AnimatedContent
                    val sharedTransitionScope = this@SharedTransitionLayout

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(composeScreenBackgroundColor)
                    ) {
                        Box(modifier = Modifier.height(52.dp))

                        //TODO
                        //--- AdBanners here ---
                        //MainWindowAdBanner()

                        if (targetState) {
                            Column(modifier = Modifier.wrapContentSize())
                            {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        //text = stringResource(Res.string.main_activity_chose_map),
                                        text = "Please chose your map first:",
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.Bold
                                    )

                                    MenuComposable(
                                        modifier = Modifier.weight(0.2f),
                                        onDisclaymer = { viewModel.toggleDisclaymer() },
                                        onToggleListMode = { viewModel.toggleListMode() },
                                        onToggleStarRating = { viewModel.toggleStarRateMode() },
                                        onTutorial = { viewModel.toggleTutorial() },
                                        isListMode = isListMode,
                                        isStarRating = isStarRatingMode
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    // Suchfeld
                                    MapSearchBar(
                                        searchQuery = searchQueryMaps,
                                        updateMapsSearchQuery = { viewModel.updateMapsSearchQuery(it) },
                                        modifier = Modifier.weight(1f),
                                        label = "Search for map..."
                                    )
                                    //TODO show when ML detects something
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                if (mapList.isEmpty()) {
                                    Text("Loading maps or no maps found")
                                } else {
                                    LazyVerticalGrid(
                                        contentPadding = PaddingValues(bottom = 180.dp),
                                        columns = GridCells.Adaptive(
                                            minSize = 140.dp
                                        )
                                    ) {
                                        items(mapList) { map ->
                                            val mapShape = RoundedCornerShape(4.dp)
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .weight(1f)
                                                    .padding(2.dp)
                                                    .background(
                                                        composeMapTextColor.copy(alpha = 0.7f),
                                                        shape = mapShape
                                                    )
                                                    .sharedBounds(
                                                        sharedContentState = rememberSharedContentState(
                                                            key = "image$map"
                                                        ),
                                                        animatedVisibilityScope = animatedVisibilityScope
                                                    )
                                                    .border(
                                                        1.dp,
                                                        composeTextColor,
                                                        shape = mapShape
                                                    )
                                                    .clip(mapShape)
                                                    .clickable {
                                                        viewModel.setChosenMapByName(map)
                                                        targetStateMapName = map
                                                    },
                                                contentAlignment = Alignment.Center
                                            ) {

                                                Image(
                                                    modifier = Modifier
                                                        .fillMaxSize(),
                                                    contentScale = ContentScale.Crop,
                                                    painter = painterResource(
                                                        resource = Utilitys.mapMapNameToDrawable(
                                                            map
                                                        )!!,
                                                    ),
                                                    contentDescription = map
                                                )

                                                Box(
                                                    contentAlignment = Alignment.BottomCenter,
                                                    modifier = Modifier
                                                        .align(Alignment.BottomCenter)
                                                        .height(84.dp)
                                                        .fillMaxWidth()
                                                        .background(
                                                            brush = Brush.verticalGradient(
                                                                colors = listOf(
                                                                    Color.Black.copy(alpha = 0.0f), // Start: Transparentes Schwarz (oder ein helleres Schwarz)
                                                                    Color.Black.copy(alpha = 0.3f), // Optional: Ein Übergangspunkt
                                                                    Color.Black.copy(alpha = 0.7f), // Optional: Ein weiterer Übergangspunkt
                                                                    Color.Black                     // Ende: Vollständig deckendes Schwarz
                                                                )
                                                            )
                                                        )
                                                ) {

                                                    Text(
                                                        modifier = Modifier
                                                            .fillMaxWidth(),
                                                        text = map,
                                                        /*stringResource(
                                                                                                       mapMapNameToStringRessource(
                                                                                                           map
                                                                                                       )!!)*/
                                                        color = Color.White,
                                                        fontSize = 14.sp,
                                                        textAlign = TextAlign.Center,
                                                    )

                                                }
                                            }
                                        }
                                        item(
                                            span = { GridItemSpan(maxLineSpan) })
                                        {
                                            //TODO
                                            /*
                                        MainWindowAdBanner(
                                            modifier = Modifier
                                                .padding(top = 8.dp)
                                        )    */
                                        }
                                        item {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(48.dp)
                                            ) { }
                                        }
                                    }
                                }
                            }
                        } else {
                            Row {
                                val shape = RoundedCornerShape(4.dp)
                                Spacer(modifier = Modifier.weight(0.15f))
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 8.dp, end = 8.dp)
                                        .background(
                                            composeScreenBackgroundColor,
                                            shape = shape
                                        )
                                        .height(48.dp)
                                        .border(1.dp, composeTextColor, shape = shape)
                                        .clickable {
                                            viewModel.clearChoosenMap()
                                        }
                                        .clip(shape)
                                        .sharedBounds(
                                            sharedContentState = rememberSharedContentState(key = "image$targetStateMapName"),
                                            animatedVisibilityScope = animatedVisibilityScope
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Image(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentScale = ContentScale.Crop,
                                        painter = painterResource(
                                            resource = Utilitys.mapMapNameToDrawable(
                                                targetStateMapName
                                            )!!
                                        ),
                                        contentDescription = choosenMap,
                                        colorFilter = ColorFilter.tint(
                                            Color.Black.copy(alpha = 0.5f),
                                            blendMode = BlendMode.Darken
                                        )
                                    )

                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {

                                        Text(
                                            //TODO string resource
                                            text = targetStateMapName,
                                            fontSize = 20.sp,
                                            color = Color.White, // Besser lesbar auf dunklem Gradienten
                                            overflow = Ellipsis,
                                            textAlign = TextAlign.Center,
                                            maxLines = 1,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(top = 12.dp, start = 12.dp, end = 12.dp),
                                        )

                                    }
                                }

                                MenuComposable(
                                    modifier = Modifier.weight(0.24f),
                                    onDisclaymer = { viewModel.toggleDisclaymer() },
                                    onToggleListMode = { viewModel.toggleListMode() },
                                    onToggleStarRating = { viewModel.toggleStarRateMode() },
                                    onTutorial = { viewModel.toggleTutorial() },
                                    isListMode = isListMode,
                                    isStarRating = isStarRatingMode
                                )
                            }
                        }


                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        ) { }

                        //Composable um das tracken der Champs mit der Videostream zu testen
                        /*if (isStreamingEnabled) {
                        VideoStreamComposable()
                    }*/

                        //Composable um das tracken der Champs mit der Kamera zu testen
                        /*CameraComposable(
                onObjectsDetected = { labels -> detectedObjectLabels = labels }
            )

            Text(
                modifier = Modifier.padding(16.dp),
                text = if (detectedObjectLabels.isNotEmpty()) {
                    "Zuletzt erkannte Objekte: ${detectedObjectLabels.joinToString(", ")}"
                } else {
                    "Keine Objekte erkannt."
                }
            )*/

                        if (choosenMap.isNotEmpty()) {
                            if (!(theirPickedChamps.isEmpty() && ownPickedChamps.isEmpty())) {
                                ListOfPickedChampsComposable(
                                    ownPickedChamps = ownPickedChamps,
                                    theirPickedChamps = theirPickedChamps,
                                    composeTextColor = composeTextColor,
                                    removePick = { i, teamSide ->
                                        viewModel.removePick(
                                            i,
                                            teamSide
                                        )
                                    },
                                    ownPickScore = ownPickScore,
                                    theirPickScore = theirPickScore,
                                    isStarrating = isStarRatingMode
                                )
                            }
                            val favFilter by viewModel.favFilter.collectAsState(false)
                            SearchAndFilterRowForChampsSmall(
                                searchQueryOwnTChamps = searchQueryOwnTChamps,
                                roleFilter = roleFilter,
                                favFilter = favFilter,
                                setRoleFilter = { roleEnum -> viewModel.setRoleFilter(roleEnum) },
                                updateChampSearchQuery = { queryString ->
                                    viewModel.updateChampSearchQuery(
                                        queryString
                                    )
                                },
                                toggleFavFilter = { viewModel.toggleFavFilter() },
                                //TODO
                                platformType = platformType
                            )

                            Box(modifier = Modifier.height(8.dp))

                            if (chosableChampList.isEmpty()) {
                                Text("Loading Champs or no champs found…")
                            } else {
                                val distinctChosableChampList by viewModel.distinctChosableChampList.collectAsState(
                                    emptyList()
                                )
                                val distinctAndUnfilteredChosableChampList by viewModel.distinctfilteredChosableChampList.collectAsState(
                                    emptyList()
                                )
                                val fitTeamMax by viewModel.fitTeamMax.collectAsState(1)
                                val goodAgainstTeamMax by viewModel.goodAgainstTeamMax.collectAsState(
                                    1
                                )
                                val ownScoreMax by viewModel.ownScoreMax.collectAsState(1)
                                val theirScoreMax by viewModel.theirScoreMax.collectAsState(1)
                                val choosenMap by viewModel.choosenMap.collectAsState("")
                                val isStarRatingMode by viewModel.isStarRatingMode.collectAsState()

                                if (isListMode) {
                                    AvailableChampListComposable(
                                        sortState = sortState,
                                        composeTextColor = composeTextColor,
                                        chosableChampList = chosableChampList,
                                        setSortState = { sortState ->
                                            viewModel.setSortState(
                                                sortState
                                            )
                                        },
                                        onButtonClick = { listState, coroutineScope ->
                                            viewModel.scrollList(
                                                listState,
                                                coroutineScope
                                            )
                                        },
                                        pickChampForTeam = { i, teamSide ->
                                            viewModel.pickChampForTeam(
                                                i,
                                                teamSide
                                            )
                                        },
                                        setBansPerTeam = { i, teamSide ->
                                            viewModel.setBansPerTeam(
                                                i,
                                                teamSide
                                            )
                                        },
                                        updateChampSearchQuery = { string ->
                                            viewModel.updateChampSearchQuery(
                                                string
                                            )
                                        },
                                        isStarRatingMode = isStarRatingMode,
                                        ownScoreMax = ownScoreMax,
                                        theirScoreMax = theirScoreMax
                                    )
                                } else {
                                    AvailableChampPortraitComposable(
                                        sortState = sortState,
                                        distinctChosableChampList = distinctChosableChampList,
                                        distinctAndUnfilteredChosableChampList = distinctAndUnfilteredChosableChampList,
                                        fitTeamMax = fitTeamMax,
                                        goodAgainstTeamMax = goodAgainstTeamMax,
                                        ownScoreMax = ownScoreMax,
                                        theirScoreMax = theirScoreMax,
                                        choosenMap = choosenMap,
                                        isStarRatingMode = isStarRatingMode,
                                        setSortState = { sortState ->
                                            viewModel.setSortState(
                                                sortState
                                            )
                                        },
                                        scrollList = { lazyListState, coroutineScope ->
                                            viewModel.scrollList(
                                                lazyListState,
                                                coroutineScope
                                            )
                                        },
                                        toggleFavoriteStatus = { string ->
                                            viewModel.toggleFavoriteStatus(
                                                string
                                            )
                                        },
                                        pickChampForOwnTeam = { i, teamSide ->
                                            viewModel.pickChampForTeam(
                                                i,
                                                teamSide
                                            )
                                        },
                                        updateChampSearchQuery = { string ->
                                            viewModel.updateChampSearchQuery(
                                                string
                                            )
                                        },
                                        setBansPerTeam = { i, teamSide ->
                                            viewModel.setBansPerTeam(
                                                i,
                                                teamSide
                                            )
                                        },
                                        platformType = platformType
                                    )
                                }
                            }
                        }
                    }
                    if (isDisclaymerShown) {
                        Column {
                            Box(modifier = Modifier.height(48.dp))
                            DisclaimerComposable(onClose = { viewModel.toggleDisclaymer() })
                        }
                    }

                    //TODO
                    if (isTutorialShown /*|| isFirstStart*/) {
                        Column {
                            Box(modifier = Modifier.height(48.dp))
                            TutorialCarouselComposable(
                                modifier = Modifier.fillMaxSize(),
                                onClose = { viewModel.toggleTutorial() })
                        }
                    }
                }
            }
        }
    }
}
