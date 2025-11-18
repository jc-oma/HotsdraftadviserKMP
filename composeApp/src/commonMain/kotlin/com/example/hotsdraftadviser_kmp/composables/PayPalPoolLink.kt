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
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import hotsdraftadviser_kmp.composeapp.generated.resources.qrcode_to_pool

@Preview
@Composable
fun PayPalPoolLink() {
    val uriHandler = LocalUriHandler.current
    val paypalPoolLink = "https://www.paypal.com/pools/c/9k9v9FP5AI"

    Column {
        Text(
            modifier = Modifier
                .width(160.dp)
                .padding(16.dp)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable {
                    uriHandler.openUri(paypalPoolLink)
                }, text = "Support hosting costs", fontSize = 20.sp,
            textDecoration = TextDecoration.Underline, color = Color.White
        )
        // 2. Das Image Composable
        Image(
            painter = painterResource(Res.drawable.qrcode_to_pool),
            contentDescription = "Support me with bill",
            modifier = Modifier
                .size(width = 160.dp, height = 160.dp)
                .padding(16.dp)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable {
                    uriHandler.openUri(paypalPoolLink)
                }
        )
    }
}
