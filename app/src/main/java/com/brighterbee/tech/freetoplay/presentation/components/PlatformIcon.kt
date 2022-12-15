package com.brighterbee.tech.freetoplay.presentation.game_details.components

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.brighterbee.tech.freetoplay.R

@Composable
fun PlatformIcon(platform: String) {
    Icon(
        painter = painterResource(
            id = if (platform.contains("windows", ignoreCase = true))
                R.drawable.ic_windows_brands
            else
                R.drawable.ic_window_maximize_solid
        ),
        contentDescription = "Genre Icon",
        tint = MaterialTheme.colors.primaryVariant,
    )
}
