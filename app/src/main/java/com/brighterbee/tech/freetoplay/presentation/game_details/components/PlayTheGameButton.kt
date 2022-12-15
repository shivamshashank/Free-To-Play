package com.brighterbee.tech.freetoplay.presentation.game_details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.brighterbee.tech.freetoplay.R

@Composable
fun PlayTheGameButton(
    onPlayTheGameClicked: () -> Unit,
) {
    Button(
        onClick = onPlayTheGameClicked,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colors.onPrimary,
            backgroundColor = MaterialTheme.colors.primary,
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sign_in_alt_solid),
                contentDescription = "Play the game Icon",
            )
            Text(text = "   " + stringResource(id = R.string.lbl_play_the_game))
        }
    }
}