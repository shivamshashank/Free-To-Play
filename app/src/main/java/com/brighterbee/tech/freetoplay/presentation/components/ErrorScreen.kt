package com.brighterbee.tech.freetoplay.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brighterbee.tech.freetoplay.R

@Composable
fun ErrorScreen(
    message: String?,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_circle_info_solid),
            contentDescription = "",
            tint = MaterialTheme.colors.onSurface,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message ?: stringResource(id = R.string.unknown_error),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.subtitle2,
        )
    }
}