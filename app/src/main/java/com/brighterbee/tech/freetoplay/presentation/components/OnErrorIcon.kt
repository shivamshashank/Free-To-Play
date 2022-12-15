package com.brighterbee.tech.freetoplay.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.brighterbee.tech.freetoplay.R

@Composable
fun OnErrorIcon() {
    Icon(
        modifier = Modifier
            .fillMaxWidth()
            .size(128.dp)
            .padding(bottom = 32.dp),
        painter = painterResource(id = R.drawable.ic_warning),
        contentDescription = "",
        tint = Color.Red,
    )
}