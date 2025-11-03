package com.example.hotsdraftadviser_kmp.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MenuComposable(
    modifier: Modifier = Modifier,
    isStarRating: Boolean,
    isListMode: Boolean,
    onDisclaymer: () -> Unit,
    onToggleListMode: () -> Unit,
    onToggleStarRating: () -> Unit,
    onTutorial: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box() {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Tutorial") },
                onClick = {
                    onTutorial()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Disclaimer") },
                onClick = {
                    onDisclaymer()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        if (isStarRating) "Number Rating" else "Star Rating"
                    )
                },
                onClick = {
                    onToggleStarRating()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Row {
                        if (isListMode) {
                            Icon(Icons.Outlined.AccountBox, contentDescription = "List")
                        } else {
                            Icon(Icons.AutoMirrored.Filled.List, contentDescription = "List")
                        }
                    }
                },
                onClick = {
                    onToggleListMode()
                    expanded = false
                }
            )
        }
    }
}

@Preview
@Composable
fun MenuMainActivitPreview() {
    MenuComposable(
        onDisclaymer = {}, onToggleListMode = {}, onTutorial = {},
        onToggleStarRating = {}, isListMode = true, isStarRating = true
    )
}