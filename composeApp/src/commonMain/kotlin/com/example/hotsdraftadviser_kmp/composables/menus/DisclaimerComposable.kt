package com.example.hotsdraftadviser_kmp.composables.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun DisclaimerComposable(onClose: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier.padding(18.dp),
            // TODO stringResource(id = R.string.disclaimer)
            text = "Heroes of the Storm™ is a trademark or registered trademark of Blizzard Entertainment, Inc., in the U.S. and/or other countries. HotsDraftAdviser is not associated with or endorsed by Blizzard Entertainment."
        )
        Button(
            onClick = { onClose() },
        ) {
            Text(text = "CLOSE",
                style = MaterialTheme.typography.labelLarge)
        }
    }
}