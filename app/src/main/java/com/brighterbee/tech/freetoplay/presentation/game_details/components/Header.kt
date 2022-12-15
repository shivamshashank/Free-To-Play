package com.brighterbee.tech.freetoplay.presentation.game_details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Header(label: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = label,
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Start,
    )
}
