package com.example.hotsdraftadviser_kmp.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hotsdraftadviser_kmp.composeapp.generated.resources.Res
import hotsdraftadviser_kmp.composeapp.generated.resources.qrcode_to_app
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun GooglePlayLinkImage() {
    val uriHandler = LocalUriHandler.current
    val playStoreUrl =
        "https://play.google.com/store/apps/details?id=com.jcdevelopment.hotsdraftadviser"

    Column {
        Text(
            modifier = Modifier
                .width(160.dp)
                .padding(16.dp)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable {
                uriHandler.openUri(playStoreUrl)
            }, text = "Get the mobile App", fontSize = 20.sp,
            textDecoration = TextDecoration.Underline, color = Color.White
        )
        // 2. Das Image Composable
        Image(
            painter = painterResource(Res.drawable.qrcode_to_app),
            contentDescription = "Get it on Google Play",
            modifier = Modifier
                .size(width = 160.dp, height = 160.dp)
                .padding(16.dp)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable {
                    uriHandler.openUri(playStoreUrl)
                }
        )
    }
}
