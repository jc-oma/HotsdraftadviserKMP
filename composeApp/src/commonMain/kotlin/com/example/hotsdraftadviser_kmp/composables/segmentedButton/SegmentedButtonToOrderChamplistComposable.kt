package com.example.hotsdraftadviser_kmp.composables.segmentedButton

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.hotsdraftadviser_kmp.composables.getColorByHexString
import com.example.hotsdraftadviser_kmp.enums.SortState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SegmentedButtonToOrderChamplistComposable(
    setSortState: (SortState) -> Unit,
    sortState: SortState,
    onButtonClick: () -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(sortState.ordinal) }
    val list = listOf<String>("Best Pick",
        "Best Ban",
        "Name"
    )

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding( start = 160.dp, end = 160.dp)
    ) {
        for ((i, state) in SortState.entries.withIndex()) {
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = i,
                    count = SortState.entries.size
                ),
                onClick = {
                    selectedIndex = i
                    setSortState(state)
                    onButtonClick()
                },
                selected = i == selectedIndex,
                label = { Text(text =list[i], maxLines = 1, overflow = TextOverflow.StartEllipsis, color = Color.White) }
            )
        }
    }
}

@Preview
@Composable
private fun SegmentedPreview() {
    SegmentedButtonToOrderChamplistComposable(
        setSortState = {},
        sortState = SortState.OWNPOINTS,
        onButtonClick = {}
    )
}