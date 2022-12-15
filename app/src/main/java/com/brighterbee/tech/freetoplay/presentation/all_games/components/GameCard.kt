package com.brighterbee.tech.freetoplay.presentation.all_games.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brighterbee.tech.freetoplay.R
import com.brighterbee.tech.freetoplay.domain.model.Game
import com.brighterbee.tech.freetoplay.presentation.Screen
import com.brighterbee.tech.freetoplay.presentation.components.NetworkImage
import com.brighterbee.tech.freetoplay.presentation.components.OnErrorIcon
import com.brighterbee.tech.freetoplay.presentation.game_details.components.PlatformIcon

@Composable
fun GameCard(
    game: Game,
    navController: NavController,
) {
    Card(
        modifier = Modifier
            .clickable {
                navController.navigate(Screen.GameDetailsScreen.route + "/${game.id}")
            }
            .height(280.dp),
        shape = MaterialTheme.shapes.small,
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                NetworkImage(
                    modifier = Modifier.fillMaxHeight(0.5f),
                    url = game.thumbnail,
                    crossFade = 1000,
                    contentScale = ContentScale.Crop,
                    onLoading = { },
                    onError = {
                        OnErrorIcon()
                    },
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = game.title,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = game.shortDescription,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(24.dp),
                    ),
                    elevation = 8.dp,
                    shape = RoundedCornerShape(24.dp),
                    color = MaterialTheme.colors.primaryVariant,
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp),
                        text = game.genre,
                        style = MaterialTheme.typography.caption,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                PlatformIcon(platform = game.platform)
            }
        }

    }
}