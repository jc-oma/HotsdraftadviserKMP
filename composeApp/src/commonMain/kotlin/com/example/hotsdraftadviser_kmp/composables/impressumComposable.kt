package com.example.hotsdraftadviser_kmp.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ImpressumComposable() {
//TODO
}

@Preview
@Composable
fun DatenschutzComposable() {
    Text("This website does not collect any personal information. The Draft System uses completely anonymous data to ensure correct usage and avoid duplicate voting. No IP addresses are saved.")
}

@Preview
@Composable
fun DisclaimerComposable() {
    Text("Heroes of the Storm™ is a trademark or registered trademark of Blizzard Entertainment, Inc., in the U.S. and/or other countries.\n" +
            "HeroesCounters is not associated with or endorsed by Blizzard Entertainment. Sponsor:")
}