package com.example.hotsdraftadviser_kmp.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview(widthDp = 1920, heightDp = 1080)
@Composable
fun ImpressumComposable(color: Color = Color.Black, onClose: () -> Unit = {}) {
    var email by remember { mutableStateOf("click to show") }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
            .padding(start = 260.dp, end = 260.dp, top = 80.dp, bottom = 80.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "legal notice",
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            )
            Text("JC", color = color)
            Row {
                Text("E-Mail: ", color = color)
                Text(
                    modifier = Modifier.clickable(onClick = { email = "spookyhalloweenapp@gmail.com" }),
                    textDecoration = TextDecoration.Underline,
                    text = email,
                    color = color
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))

            DatenschutzComposable()
            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                "disclaimer",
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            )
            DisclaimerComposable()
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                onClick = { onClose() },
            ) {
                Text("back")
            }
        }
    }
}

@Composable
private fun DatenschutzComposable() {
    Column {
        Text(
            "privacy policy",
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            fontSize = 20.sp
        )
        Text("This website does not collect any personal information. The Draft System uses completely anonymous data to ensure correct usage.")
    }
}

@Composable
private fun DisclaimerComposable() {
    Text(
        "Heroes of the Storm™ is a trademark or registered trademark of Blizzard Entertainment, Inc., in the U.S. and/or other countries.\n" +
                "I'm not associated with or endorsed by Blizzard Entertainment."
    )
}