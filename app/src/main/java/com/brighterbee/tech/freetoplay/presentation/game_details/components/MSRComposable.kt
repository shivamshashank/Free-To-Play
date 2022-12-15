package com.brighterbee.tech.freetoplay.presentation.game_details.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun MSRComposable(
    labelId: Int,
    value: String,
) {
    Text(
        text = stringResource(id = labelId),
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.onBackground,
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = value,
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onBackground,
    )
    Spacer(modifier = Modifier.height(12.dp))
}