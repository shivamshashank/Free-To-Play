package com.brighterbee.tech.freetoplay.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.brighterbee.tech.freetoplay.R

@Composable
fun Drawer() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colors.background),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 48.dp),
            painter = painterResource(
                id = R.drawable.ic_free_to_play_launcher
            ),
            contentDescription = "Icon Content Description",
        )
        DrawerItem(
            painter = painterResource(id = R.drawable.ic_bars_staggered_solid),
            label = stringResource(id = R.string.lbl_home),
        ) {
        }
        DrawerItem(
            painter = painterResource(id = R.drawable.ic_windows_brands),
            label = stringResource(id = R.string.lbl_pc_games),
        ) {

        }
        DrawerItem(
            painter = painterResource(id = R.drawable.ic_window_maximize_solid),
            label = stringResource(id = R.string.lbl_web_games),
        ) {
        }
        DrawerItem(
            painter = painterResource(id = R.drawable.ic_arrow_trend_up_solid),
            label = stringResource(id = R.string.lbl_latest_games),
        ) {

        }

    }
}