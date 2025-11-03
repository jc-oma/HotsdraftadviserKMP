package com.example.hotsdraftadviser_kmp.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hotsdraftadviser_kmp.Utilitys.getResponsiveFontSize
import com.example.hotsdraftadviser_kmp.composables.searchbar.ChampSearchBar
import com.example.hotsdraftadviser_kmp.enums.RoleEnum
import hotsdraftadviser_kmp.composeapp.generated.resources.Res
import hotsdraftadviser_kmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndFilterRowForChampsSmall(
    searchQueryOwnTChamps: String,
    roleFilter: List<RoleEnum>,
    favFilter: Boolean,
    setRoleFilter: (RoleEnum?) -> Unit,
    updateChampSearchQuery: (String) -> Unit,
    toggleFavFilter: () -> Unit,
    isTablet: Boolean
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            ChampSearchBar(
                modifier = Modifier.weight(2f),
                searchQueryOwnTChamps,
                setRoleFilter,
                updateChampSearchQuery
            )
        }
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            val imagePadding = 8.dp
            val responsiveFontSize = getResponsiveFontSize()

            Row(modifier = Modifier.padding(top = imagePadding)) {
                val modifier = if (isTablet) Modifier
                    .weight(0.5f)
                    .padding(start = imagePadding, end = imagePadding)
                    .height(48.dp)
                else
                    Modifier
                        .weight(0.5f)
                        .padding(start = imagePadding, end = imagePadding)

                val modifierIcon = if (isTablet) Modifier.fillMaxSize().padding(4.dp) else
                    Modifier.size(FilterChipDefaults.IconSize)

                FilterChip(
                    modifier = modifier,
                    leadingIcon = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painterResource(resource = Res.drawable.tank),
                                contentDescription = "Description of your image",
                                modifier = modifierIcon
                            )
                        }
                    },
                    selected = roleFilter.contains(RoleEnum.tank),
                    onClick = { setRoleFilter(RoleEnum.tank) },
                    label = {}
                )
                FilterChip(
                    modifier = modifier,
                    leadingIcon = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painterResource(resource = Res.drawable.ranged),
                                contentDescription = "Description of your image",
                                modifier = modifierIcon
                            )
                        }
                    },
                    selected = roleFilter.contains(RoleEnum.ranged),
                    onClick = { setRoleFilter(RoleEnum.ranged) },
                    label = {}
                )
                FilterChip(
                    modifier = modifier,
                    leadingIcon = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painterResource(resource = Res.drawable.melee),
                                contentDescription = "Description of your image",
                                modifier = modifierIcon
                            )
                        }
                    },
                    selected = roleFilter.contains(RoleEnum.melee),
                    onClick = { setRoleFilter(RoleEnum.melee) },
                    label = {}
                )
                FilterChip(
                    modifier = modifier,
                    leadingIcon = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painterResource(resource = Res.drawable.heiler),
                                contentDescription = "Description of your image",
                                modifier = modifierIcon
                            )
                        }
                    },
                    selected = roleFilter.contains(RoleEnum.heal),
                    onClick = { setRoleFilter(RoleEnum.heal) },
                    label = {}
                )
                FilterChip(
                    modifier = modifier,
                    leadingIcon = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painterResource(resource = Res.drawable.bruiser),
                                contentDescription = "Description of your image",
                                modifier = modifierIcon
                            )
                        }
                    },
                    selected = roleFilter.contains(RoleEnum.bruiser),
                    onClick = { setRoleFilter(RoleEnum.bruiser) },
                    label = {}
                )
                FilterChip(
                    modifier = modifier,
                    leadingIcon = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painterResource(resource = Res.drawable.support),
                                contentDescription = "Description of your image",
                                modifier = modifierIcon
                            )
                        }
                    },
                    selected = roleFilter.contains(RoleEnum.support),
                    onClick = { setRoleFilter(RoleEnum.support) },
                    label = {}
                )
                FilterChip(
                    modifier = modifier,
                    leadingIcon = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = (Icons.Filled.Favorite),
                                contentDescription = "Heart",
                                modifier = modifierIcon
                            )
                        }
                    },
                    selected = favFilter,
                    onClick = { toggleFavFilter() },
                    label = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchAndFilterRowForChampsSmallPreview() {
    SearchAndFilterRowForChampsSmall(
        searchQueryOwnTChamps = "Hammer",
        roleFilter = listOf(RoleEnum.tank, RoleEnum.melee),
        favFilter = false,
        setRoleFilter = {},
        updateChampSearchQuery = {},
        toggleFavFilter = {},
        isTablet = true
    )
}