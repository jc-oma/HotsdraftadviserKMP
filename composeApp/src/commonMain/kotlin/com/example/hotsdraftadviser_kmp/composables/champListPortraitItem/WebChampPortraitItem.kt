package com.example.hotsdraftadviser_kmp.composables.champListPortraitItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.hotsdraftadviser_kmp.dataclasses.ChampData
import com.example.hotsdraftadviser_kmp.dataclasses.exampleChampDataSgtHammer
import com.example.hotsdraftadviser_kmp.enums.PlatformType
import hotsdraftadviser_kmp.composeapp.generated.resources.Res
import hotsdraftadviser_kmp.composeapp.generated.resources.sgthammer_card_portrait
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.max

@Composable
fun WebChampPortraitItemComposable(
    champ: ChampData,
    toggleChampFavorite: () -> Unit,
    pickChampForOwnTeam: () -> Unit,
    pickChampForTheirTeam: () -> Unit,
    updateChampSearchQuery: () -> Unit,
    ownBan: () -> Unit,
    theirBan: () -> Unit,
    champDrawable: DrawableResource,
    index: Int,
    mapFloat: Float,
    ownTeamFloat: Float,
    theirTeamFloat: Float,
    mapName: String,
    maxOwnScore: Int,
    maxTheirScore: Int,
    isStarRating: Boolean,
    platformType: PlatformType
) {
    val scoreOwnPercent = max((champ.scoreOwn.toFloat() / maxOwnScore.toFloat() * 100).toInt(), 0)
    val scoreTheirPercent = max((champ.scoreTheir.toFloat() / maxTheirScore.toFloat() * 100).toInt(), 0)

    Row {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(resource = champDrawable),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
            Box(
                Modifier.background(color = Color.White)
            ) {
                Row {
                    Text(
                        modifier = Modifier.padding(8.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = (index + 1).toString() + ". " + champ.ChampName
                    )
                }
            }
        }

    }
}

@Preview
@Composable
private fun WebChampPortraitItemComposablePreview() {
    WebChampPortraitItemComposable(
        champ = exampleChampDataSgtHammer,
        toggleChampFavorite = {},
        pickChampForOwnTeam = {},
        pickChampForTheirTeam = {},
        updateChampSearchQuery = {},
        ownBan = {},
        theirBan = {},
        champDrawable = Res.drawable.sgthammer_card_portrait,
        index = 0,
        mapFloat = 0.7f,
        ownTeamFloat = 0.4f,
        theirTeamFloat = 0.2f,
        mapName = "Hanamura",
        maxOwnScore = 144,
        maxTheirScore = 75,
        isStarRating = false,
        platformType = PlatformType.ANDROID
    )
}