package com.example.hotsdraftadviser_kmp

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.hotsdraftadviser_kmp.dataclasses.ChampData
import com.example.hotsdraftadviser_kmp.enums.RoleEnum
import com.example.hotsdraftadviser_kmp.enums.SortState
import com.example.hotsdraftadviser_kmp.enums.TeamSide
import hotsdraftadviser_kmp.composeapp.generated.resources.Res
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.collections.emptyList
import kotlin.collections.get
import kotlin.text.get
import kotlin.text.set

class MainViewModel() : ViewModel() {
    private val _showContent = MutableStateFlow(true)
    private val _isDisclaymerShown = MutableStateFlow(false)
    private val _targetState = MutableStateFlow(true)
    private val _isTutorialShown = MutableStateFlow(false)
    private val _isListMode = MutableStateFlow(false)
    private val _isStarRatingMode = MutableStateFlow(false)
    private val _isStreamingEnabled = MutableStateFlow(true)

    private val _allChampsData = MutableStateFlow<List<ChampData>>(emptyList())

    private val _filterMapsString = MutableStateFlow<String>("")
    private val _filterChampString = MutableStateFlow<String>("")
    private val _choosenMap = MutableStateFlow<String>("")
    private val _sortState = MutableStateFlow<SortState>(SortState.OWNPOINTS)
    private val _roleFilter = MutableStateFlow<List<RoleEnum>>(emptyList())
    private val _favFilter = MutableStateFlow<Boolean>(false)
    private val _isCookieBanner = MutableStateFlow<Boolean>(false)
    private val maxPicks = 5
    private var pickcounter: MutableMap<TeamSide, Int> =
        mutableMapOf(TeamSide.OWN to 0, TeamSide.THEIR to 0)
    val showContent: StateFlow<Boolean> = _showContent.asStateFlow()
    val champData: StateFlow<List<ChampData>> = _allChampsData.asStateFlow()
    val isDisclaymerShown: StateFlow<Boolean> = _isDisclaymerShown.asStateFlow()
    val isTutorialShown: StateFlow<Boolean> = _isTutorialShown.asStateFlow()
    val isListMode: StateFlow<Boolean> = _isListMode.asStateFlow()


    val isStarRatingMode: StateFlow<Boolean> = _isStarRatingMode.asStateFlow()

    val favFilter: StateFlow<Boolean> = _favFilter.asStateFlow()
    val isCookieBanner: StateFlow<Boolean> = _isCookieBanner.asStateFlow()

    val targetState: StateFlow<Boolean> = _targetState

    val allChampsData = _allChampsData.asStateFlow()
    val mapList: StateFlow<List<String>> = getSortedUniqueMaps()
    val filterMapsString: StateFlow<String> = _filterMapsString.asStateFlow()
    val filteredMaps: StateFlow<List<String>> = filterMapsByString(mapList, filterMapsString)

    val filterOwnChampString: StateFlow<String> = _filterChampString.asStateFlow()
    val sortState: StateFlow<SortState> = _sortState.asStateFlow()

    val pickedTheirTeamChamps: StateFlow<List<ChampData>> = getPickedTheirTeamChamps(TeamSide.THEIR)
    val pickedOwnTeamChamps: StateFlow<List<ChampData>> = getPickedTheirTeamChamps(TeamSide.OWN)

    val unfilteredChosableChampList: StateFlow<List<ChampData>> =
        dataFlowForChampListWithScores(false, false, false)

    val _choosableChampList = dataFlowForChampListWithScores(true, false, true)

    val _distinctchoosableChampList = dataFlowForChampListWithScores(true, true, true)
    val chosableChampList: StateFlow<List<ChampData>> = _choosableChampList
    val distinctChosableChampList: StateFlow<List<ChampData>> = _distinctchoosableChampList
    val allChampsDistinct = dataFlowForChampListWithScores(false, true, false)

    val distinctfilteredChosableChampList: StateFlow<List<ChampData>> = combine(
        allChampsDistinct,
        distinctChosableChampList
    ) { unfilteredList, distinctList ->
        val distinctChampNames = distinctList.map { it.ChampName }.toSet()
        unfilteredList.filter { it -> it.ChampName in distinctChampNames }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    val ownScoreMax = allChampsDistinct.map { list -> list.maxOfOrNull { it.scoreOwn } ?: 1 }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = 1
        )

    val theirScoreMax = allChampsDistinct.map { list -> list.maxOfOrNull { it.scoreTheir } ?: 1 }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = 1
        )

    val fitTeamMax: StateFlow<Int> = allChampsDistinct.map { list ->
        list.maxOfOrNull { it.fitTeam } ?: 1
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = 1
    )

    val goodAgainstTeamMax: StateFlow<Int> = combine(
        allChampsDistinct, // Assuming this contains champs with their StrongAgainst properties
        pickedTheirTeamChamps
    ) { champs, pickedTheirChamps ->
        champs.maxOfOrNull { champ ->
            champ.StrongAgainst.sumOf { strongAgainstEntry ->
                if (pickedTheirChamps.any { pickedChamp -> pickedChamp.ChampName == strongAgainstEntry.ChampName }) {
                    strongAgainstEntry.ScoreValue
                } else 0
            }
        } ?: 1
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = 1
    )

    val choosenMap: StateFlow<String> = _choosenMap

    val ownPickScore: StateFlow<Int> = getTeamAgregatedPoints(TeamSide.OWN)
    val theirPickScore: StateFlow<Int> = getTeamAgregatedPoints(TeamSide.THEIR)

    val roleFilter: StateFlow<List<RoleEnum>> = _roleFilter

    fun setSortState(sortState: SortState) {
        viewModelScope.launch {
            _sortState.value = sortState
        }
    }

    init {
        loadJson()
    }

    fun toggleDisclaymer() {
        viewModelScope.launch {
            _isDisclaymerShown.value = !_isDisclaymerShown.value
        }
    }

    fun toggleCookieBanner() {
        viewModelScope.launch {
            _isCookieBanner.value = !_isCookieBanner.value
        }
    }

    fun toggleListMode() {
        _isListMode.value = !_isListMode.value
    }

    fun toggleStarRateMode() {
        _isStarRatingMode.value = !_isStarRatingMode.value
    }

    fun toggleTutorial() {
        _isTutorialShown.value = !_isTutorialShown.value
    }

    fun updateMapsSearchQuery(query: String) {
        _filterMapsString.value = query
    }

    fun setChosenMapByName(name: String) {
        viewModelScope.launch {
            delay(550)
            _choosenMap.value = name
        }

        _targetState.value = false
    }

    fun clearChoosenMap() {
        _choosenMap.value = ""
        _targetState.value = true
    }

    fun removePick(index: Int, teamSide: TeamSide) {
        viewModelScope.launch {
            val currentChampList = _allChampsData.value
            val teamPicks = currentChampList.filter { it.isPicked && it.pickedBy == teamSide }

            if (index >= 0 && index < teamPicks.size) {
                val champToRemove = teamPicks[index]
                val updatedChamp = champToRemove.copy(isPicked = false, pickedBy = TeamSide.NONE)

                _allChampsData.value =
                    _allChampsData.value.map { if (it.ChampName == updatedChamp.ChampName) updatedChamp else it }

                if (teamPicks[index].ChampName == "Chogall") {
                    pickcounter[teamSide] = pickcounter[teamSide]!! - 2
                } else {
                    pickcounter[teamSide] = pickcounter[teamSide]!! - 1
                }

            } else {
                println(
                    "ViewModel Ungültiger Index zum Entfernen des Picks: $index für Team: $teamSide"
                )
            }
        }
    }

    fun updateChampSearchQuery(query: String) {
        _filterChampString.value = query
    }

    fun toggleFavFilter() {
        _favFilter.value = !_favFilter.value
    }

    fun setRoleFilter(role: RoleEnum?) {
        if (role == null) {
            _roleFilter.value = emptyList()
        } else {
            if (_roleFilter.value.contains(role)) {
                _roleFilter.value = _roleFilter.value.filter { it -> it != role }
            } else {
                _roleFilter.value = _roleFilter.value + role
            }
        }
    }

    fun scrollList(listState: LazyListState, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            listState.animateScrollToItem(0)
        }
    }

    fun pickChampForTeam(index: Int, teamSide: TeamSide) {
        viewModelScope.launch {
            val currentChampList = _distinctchoosableChampList.first()
            val alreadyPicked = currentChampList.filter { it.isPicked && it.pickedBy == teamSide }
            val isChogall = currentChampList[index].ChampName == "Chogall"
            var teamCounter = pickcounter[teamSide] ?: 0

            if (isChogall) {
                if (teamCounter < maxPicks - 1) {
                    pickcounter[teamSide] = teamCounter + 2
                    val pickedChamp =
                        currentChampList.find { it.ChampName == currentChampList[index].ChampName }
                            ?.copy(isPicked = true, pickedBy = teamSide)
                            ?: return@launch // Frühzeitiger Ausstieg, falls der Champ nicht gefunden wird
                    _allChampsData.value =
                        _allChampsData.value.map { if (it.ChampName == pickedChamp.ChampName) pickedChamp else it }
                }
            } else if (teamCounter < maxPicks) {
                pickcounter[teamSide] = teamCounter + 1
                val pickedChamp =
                    currentChampList.find { it.ChampName == currentChampList[index].ChampName }
                        ?.copy(isPicked = true, pickedBy = teamSide)
                        ?: return@launch // Frühzeitiger Ausstieg, falls der Champ nicht gefunden wird
                _allChampsData.value =
                    _allChampsData.value.map { if (it.ChampName == pickedChamp.ChampName) pickedChamp else it }
            }
        }
    }

    fun setBansPerTeam(i: Int, teamSide: TeamSide) {
        viewModelScope.launch {
            val currentChampList = _distinctchoosableChampList.first()
            val bannedChamp = currentChampList[i].copy(isPicked = true)
            updateChampDataWithPickStatus(bannedChamp)
        }
    }

    fun toggleFavoriteStatus(championName: String) {
        viewModelScope.launch {
            //TODO when repo
            //favoriteChampionsRepository.toggleFavoriteStatus(championName)
            checkIfChampIsFavorite()
        }
    }

    //TODO when repo
    fun incrementResetCounter() {
    }

    fun resetAll() {
        viewModelScope.launch {
            pickcounter[TeamSide.OWN] = 0
            pickcounter[TeamSide.THEIR] = 0
            _targetState.value = true
            _filterMapsString.value = ""
            _filterChampString.value = ""
            _choosenMap.value = ""
            _sortState.value = SortState.OWNPOINTS
            _roleFilter.value = emptyList()
            _allChampsData.value = _allChampsData.value.map {
                it.copy(
                    isPicked = false,
                    pickedBy = TeamSide.NONE,
                    scoreOwn = 0,
                    scoreTheir = 0
                )
            }
        }

    }

    private suspend fun checkIfChampIsFavorite() {
        _allChampsData.value = _allChampsData.value.map { champ ->
            champ.copy(
                //TODO when repo
                //isAFavoriteChamp = favoriteChampionsRepository.isChampionFavorite(champ.ChampName)
            )
        }
    }

    private fun updateChampDataWithPickStatus(
        champ: ChampData
    ) {
        val currentChampData = _allChampsData.value.toMutableList()
        val indexInAllChamps = currentChampData.indexOfFirst { it.ChampName == champ.ChampName }
        val indexIfChogal = currentChampData.indexOfLast { it.ChampName == champ.ChampName }
        if (indexInAllChamps != -1) {
            currentChampData[indexInAllChamps] =
                currentChampData[indexInAllChamps].copy(isPicked = true)
            currentChampData[indexIfChogal] =
                currentChampData[indexInAllChamps].copy(isPicked = true)
            _allChampsData.value = currentChampData.toList()
        }
    }

    private fun getPickedTheirTeamChamps(team: TeamSide): StateFlow<List<ChampData>> =
        _allChampsData.map { champs -> champs.filter { it.pickedBy == team } }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    private fun loadJson() {
        viewModelScope.launch(Dispatchers.Default) {
            val readBytes = Res.readBytes("files/output.json")
            val jsonString = readBytes.decodeToString()
            val champData = Json.decodeFromString<List<ChampData>>(jsonString)
            _allChampsData.value = champData
        }
    }

    fun toggleContent() {
        _showContent.update { !it }
    }

    private fun dataFlowForChampListWithScores(
        isFiltered: Boolean,
        isDistincted: Boolean,
        isFilterPicks: Boolean
    ): StateFlow<List<ChampData>> {
        var copy = calculateChampsPerPicks()
        var list = combine(
            copy,
            getAllFavoriteChampionNamesFlow(),
            _choosenMap,
            _sortState,
            _filterChampString
        ) { champs, allFavChamps, mapSearchString, doSortByOwn, filter ->

            val filteredByNameChamps = if (filter.isBlank()) {
                champs
            } else {
                if (isFiltered) {
                    champs.filter { champ ->
                        (champ.ChampName.contains(
                            filter,
                            ignoreCase = true
                        ))//TODO
                           /*     || champ.localName!!.contains(
                            filter,
                            ignoreCase = true
                        ))*/
                                && !champ.isPicked
                    }
                } else {
                    champs
                }
            }

            val lowerCaseSearchString = mapSearchString.lowercase()

            val filteredByPickedChamps = if (isFilterPicks) {
                filteredByNameChamps.filter { champ ->
                    !champ.isPicked
                }
            } else {
                filteredByNameChamps
            }

            val calculatedChamps = filteredByPickedChamps.map { champ ->
                val updatedChamp = champ.copy()

                if (!mapSearchString.isEmpty()) {
                    champ.MapScore.forEach { mapScore ->
                        if (mapScore.MapName.lowercase().contains(lowerCaseSearchString)) {
                            updatedChamp.scoreOwn += mapScore.ScoreValue
                            updatedChamp.scoreTheir += mapScore.ScoreValue
                        }
                    }
                }
                updatedChamp
            }


            val sortedChamps = if (doSortByOwn == SortState.OWNPOINTS) {
                calculatedChamps.sortedByDescending { it.scoreOwn } // Höchster ScoreOwn zuerst
            } else if (doSortByOwn == SortState.THEIRPOINTS) {
                calculatedChamps.sortedByDescending { it.scoreTheir } // Höchster ScoreTheir zuerst
            } else {
                calculatedChamps.sortedBy { it.ChampName }
            }

            sortedChamps
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

        // Filter 2x
        list = if (isFiltered) {
            filterChampsByRole(list)
        } else {
            list
        }

        list = if (isDistincted) {
            list.map { champs ->
                champs.distinctBy { it.ChampName }
            }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
        } else {
            list
        }

        // Normalize scores based on the maximum mapScore in the list
        list = list.map { champs ->

            val maxMapScoreOverall = champs.maxOfOrNull { champ ->
                val scoreOfCurrentMap = champ.MapScore.find { it.MapName == _choosenMap.value }
                scoreOfCurrentMap?.ScoreValue ?: 0
            } ?: 1

            val champsWithMapFloat = champs.map { champ ->
                val mapScoreForChosenMap =
                    champ.MapScore.find { it.MapName == _choosenMap.value }?.ScoreValue ?: 0
                val mapFloatValue =
                    if (maxMapScoreOverall != 0) (mapScoreForChosenMap.toFloat() / maxMapScoreOverall.toFloat()) else 0f
                champ.copy(
                    mapFloat = mapFloatValue
                )
            }

            champsWithMapFloat
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

        if (isFiltered) {
            list = filterChampsByFav(list)
        }

        list = addMapScoreToDistinctMaps(list)

        return list
    }

    //TODO when Repo available
    private fun getAllFavoriteChampionNamesFlow(): Flow<MutableList<String>> {
        return MutableStateFlow(mutableListOf(""))
    }

    private fun calculateChampsPerPicks(): StateFlow<List<ChampData>> {
        val calculatedChampsPerPick = combine(
            _allChampsData
        ) { allChamps ->
            val ownPickNames = pickedOwnTeamChamps.first().map { it.ChampName }
            val theirPickNames = pickedTheirTeamChamps.first().map { it.ChampName }

            allChamps.first().map { champ ->

                var currentScoreOwn = 0
                var currentScoreTheir = 0
                //--------------------------//
                var strongAgainstScoreOwn = 0
                var strongAgainstScoreTheir = 0

                var weakAgainstScoreOwn = 0
                var weakAgainstScoreTheir = 0

                var goodTeamWithScoreOwn = 0
                var goodTeamWithScoreTheir = 0

                champ.StrongAgainst.forEach { strongAgainstEntry ->
                    if (theirPickNames.contains(strongAgainstEntry.ChampName)) {
                        currentScoreOwn += strongAgainstEntry.ScoreValue
                        strongAgainstScoreOwn += strongAgainstEntry.ScoreValue
                    }

                    if (ownPickNames.contains(strongAgainstEntry.ChampName)) {
                        currentScoreTheir += strongAgainstEntry.ScoreValue
                        strongAgainstScoreTheir += strongAgainstEntry.ScoreValue
                    }
                }

                //TODO überprüfen ob das passt - eventuell doppelung mit dadrüber
                champ.WeakAgainst.forEach { weakAgainstEntry ->
                    if (theirPickNames.contains(weakAgainstEntry.ChampName)) {
                        currentScoreOwn -= weakAgainstEntry.ScoreValue
                        weakAgainstScoreOwn += weakAgainstEntry.ScoreValue
                    }
                    if (ownPickNames.contains(weakAgainstEntry.ChampName)) {
                        currentScoreTheir -= weakAgainstEntry.ScoreValue
                        weakAgainstScoreTheir += weakAgainstEntry.ScoreValue
                    }
                }

                champ.GoodTeamWith.forEach { goodTeamEntry ->
                    if (ownPickNames.contains(goodTeamEntry.ChampName)) {
                        currentScoreOwn += goodTeamEntry.ScoreValue
                        goodTeamWithScoreOwn += goodTeamEntry.ScoreValue
                    }
                    if (theirPickNames.contains(goodTeamEntry.ChampName)) {
                        currentScoreTheir += goodTeamEntry.ScoreValue
                        goodTeamWithScoreTheir += goodTeamEntry.ScoreValue
                    }
                }

                champ.copy(
                    scoreOwn = currentScoreOwn,
                    scoreTheir = currentScoreTheir,
                    fitTeam = goodTeamWithScoreOwn,
                    goodAgainstTeam = strongAgainstScoreOwn - weakAgainstScoreOwn
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
        return calculatedChampsPerPick
    }

    private fun filterChampsByRole(flow: StateFlow<List<ChampData>>): StateFlow<List<ChampData>> {
        return combine(flow, _roleFilter) { champs, selectedRoles ->
            if (selectedRoles.isEmpty()) {
                champs
            } else {
                champs.filter { champ ->
                    selectedRoles.any { it ->
                        val ccrole = champ.ChampRoleAlt
                        ccrole.contains(it)
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
    }

    private fun filterChampsByFav(list: StateFlow<List<ChampData>>): StateFlow<List<ChampData>> {
        return combine(list, _favFilter) { champs, favFilter ->
            if (favFilter) {
                champs.filter { it.isAFavoriteChamp }
            } else {
                champs
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
    }

    private fun addMapScoreToDistinctMaps(list: StateFlow<List<ChampData>>): StateFlow<List<ChampData>> {
        return list.map { champs ->
            champs.map { champ ->
                val distinctMapScores = champ.MapScore
                    .groupBy { it.MapName }
                    .map { (mapName, scores) ->
                        scores.first().copy(ScoreValue = scores.sumOf { it.ScoreValue })
                    }
                champ.copy(MapScore = distinctMapScores)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
    }

    private fun getTeamAgregatedPoints(teamSide: TeamSide): StateFlow<Int> {
        return combine(unfilteredChosableChampList) { champsWithScores ->
            var totalScore = 0
            val pickedChamp = champsWithScores.first().filter { it -> it.pickedBy == teamSide }
            pickedChamp.forEach { pickedChamp ->
                champsWithScores.first().find { it.ChampName == pickedChamp.ChampName }
                    ?.let { matchedChamp ->
                        totalScore += matchedChamp.scoreOwn
                    }
            }
            println("ViewModel: Aggregated Own Team Score: $totalScore")
            totalScore
        }.stateIn(viewModelScope, SharingStarted.Lazily, 0)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getSortedUniqueMaps(): StateFlow<List<String>> {
        val list = _allChampsData.map { champs ->
            champs.map { champ ->
                champ.MapScore.map { map ->
                    map.MapName
                }.distinct()
                    .sorted()
            }
        }
            .map { nestedList ->
                nestedList.flatten()
                    .distinct()
            }
            .distinctUntilChanged()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = emptyList()
            )
        return list
    }

    private fun filterMapsByString(
        maps: StateFlow<List<String>>,
        searchString: StateFlow<String>
    ): StateFlow<List<String>> {
        return combine(maps, searchString) { currentMaps, currentSearchString ->
            val lowerCaseSearchString = currentSearchString.lowercase()
            //TODO string ressources for all languaghes
            //val application = getApplication<Application>()

            currentMaps.filter { item ->
                item.lowercase().contains(lowerCaseSearchString) /*||
                        application.getString(Utilitys.mapMapNameToStringRessource(item)!!)
                            .lowercase().contains(lowerCaseSearchString)*/
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
    }
}

// ViewModelFactory that retrieves the data repository for your app.
val mainViewModelFactory = viewModelFactory {
    initializer {
        MainViewModel()
    }
}

// siehe: https://developer.android.com/kotlin/multiplatform/viewmodel?hl=de
//fun getDataRepository(): DataRepository = DataRepository()