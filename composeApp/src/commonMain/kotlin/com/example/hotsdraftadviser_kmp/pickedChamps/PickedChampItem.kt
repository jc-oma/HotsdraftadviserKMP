package com.example.hotsdraftadviser_kmp.pickedChamps

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.hotsdraftadviser_kmp.Utilitys
import com.example.hotsdraftadviser_kmp.composables.getColorByHexString
import com.example.hotsdraftadviser_kmp.dataclasses.ChampData
import com.example.hotsdraftadviser_kmp.dataclasses.exampleChampDataSgtHammer
import hotsdraftadviser_kmp.composeapp.generated.resources.Res
import hotsdraftadviser_kmp.composeapp.generated.resources.sgthammer_card_portrait
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RowScope.PickedChampItem(
    teamColor: Color,
    textColor: Color,
    removePickForTeam: () -> Unit,
    teamPickedChamp: ChampData,
    painter: Painter,
    modifier: Modifier = Modifier
) {
    val height = 32.dp
    Box(
        modifier = modifier
            .weight(1f)
            .padding(2.dp)
            .heightIn(min = height)
            .background(
                Color.Black.copy(alpha = 1.0f),
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                3.dp,
                teamColor.copy(alpha = 1.0f),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { removePickForTeam() },
        contentAlignment = Alignment.Center
    ) {
        val name: String = if (teamPickedChamp.localName == null) {
            teamPickedChamp.ChampName
        } else teamPickedChamp.localName!!

        Image(
            modifier = Modifier
                .height(height)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = name,
            colorFilter = ColorFilter.tint(
                Color.Black.copy(alpha = 0.5f),
                blendMode = BlendMode.Darken
            )
        )
        /*
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.0f))
        )*/
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 12.dp, top = 2.dp, bottom = 2.dp)
                    .height(18.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    teamPickedChamp.ChampRoleAlt.forEach { it ->
                        Icon(
                            painter = painterResource(Utilitys.mapRoleToImageRessource(it)!!),
                            contentDescription = it.name,
                            tint = Color.White,
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                text = name,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                maxLines = 1,
                color = Color.White
            )
        }
    }
}


@Preview
@Composable
private fun PickedsChampItemPreview() {
    val textColor = "f8f8f9ff"
    val theirTeamColor = "5C1A1BFF"

    Row {
        PickedChampItem(
            teamColor = getColorByHexString(theirTeamColor),
            textColor = getColorByHexString(textColor),
            removePickForTeam = {},
            teamPickedChamp = exampleChampDataSgtHammer,
            painter = painterResource(
                resource = Res.drawable.sgthammer_card_portrait
            )
        )
    }
}