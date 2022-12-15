package com.brighterbee.tech.freetoplay.presentation.game_details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.brighterbee.tech.freetoplay.R
import com.brighterbee.tech.freetoplay.presentation.components.*
import com.brighterbee.tech.freetoplay.presentation.game_details.components.*
import com.brighterbee.tech.freetoplay.presentation.theme.Gray400
import com.brighterbee.tech.freetoplay.presentation.theme.RedErrorLight

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameDetailsScreen(
    navController: NavController,
    viewModel: GameDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val uriHandler = LocalUriHandler.current

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colors.onSurface,
            )
        }

        if (state.error.isNotBlank()) {
            ErrorScreen(message = state.error)
        }

        if (state.gameDetails != null) {
            val gameDetails = state.gameDetails

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = gameDetails.title,
                                style = MaterialTheme.typography.h4,
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.navigateUp()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_back),
                                    contentDescription = "Back Icon Button",
                                )
                            }
                        },
                        backgroundColor = MaterialTheme.colors.background,
                        elevation = 0.dp,
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                        .verticalScroll(state = rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (gameDetails.screenshots.isEmpty()) {
                        NetworkImage(
                            modifier = Modifier.fillMaxHeight(0.6f),
                            url = gameDetails.thumbnail,
                            crossFade = 1000,
                            contentScale = ContentScale.Crop,
                            onLoading = { },
                            onError = {
                                OnErrorIcon()
                            },
                        )
                    } else {
                        CarouselView(
                            gameDetails.screenshots.map { it.image },
                            0.8f,
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Header(label = stringResource(id = R.string.lbl_about) + " " + gameDetails.title)
                        Spacer(modifier = Modifier.height(12.dp))
                        ExpandableText(
                            text = gameDetails.description.substring(0, 120),
                            expandedText = gameDetails.description,
                            expandedTextButton = " (Show More)",
                            shrinkTextButton = " (Show Less)",
                            textStyle = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.onBackground
                            ),
                            expandedTextStyle = MaterialTheme.typography.body1.copy(
                                color = MaterialTheme.colors.onBackground
                            ),
                            expandedTextButtonStyle = MaterialTheme.typography.button.copy(
                                color = RedErrorLight
                            ),
                            shrinkTextButtonStyle = MaterialTheme.typography.button.copy(
                                color = RedErrorLight
                            ),
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Header(label = stringResource(id = R.string.lbl_extra))
                        Spacer(modifier = Modifier.height(12.dp))
                        ExtraRow(
                            firstTitle = stringResource(id = R.string.lbl_title),
                            secondTitle = stringResource(id = R.string.lbl_developer),
                            textColor = MaterialTheme.colors.onSurface,
                        )
                        ExtraRow(
                            firstTitle = gameDetails.title,
                            secondTitle = gameDetails.developer,
                            textColor = MaterialTheme.colors.onBackground,
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        ExtraRow(
                            firstTitle = stringResource(id = R.string.lbl_publisher),
                            secondTitle = stringResource(id = R.string.lbl_release_date),
                            textColor = MaterialTheme.colors.onSurface,
                        )
                        ExtraRow(
                            firstTitle = gameDetails.publisher,
                            secondTitle = gameDetails.releaseDate,
                            textColor = MaterialTheme.colors.onBackground,
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        ExtraRow(
                            firstTitle = stringResource(id = R.string.lbl_genre),
                            secondTitle = stringResource(id = R.string.lbl_platform),
                            textColor = MaterialTheme.colors.onSurface,
                        )
                        ExtraRow(
                            firstTitle = gameDetails.genre,
                            secondTitle = "  " + gameDetails.platform,
                            textColor = MaterialTheme.colors.onBackground,
                            icon = {
                                PlatformIcon(platform = gameDetails.platform)
                            }
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        gameDetails.minimumSystemRequirements?.let {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.Start,
                            ) {
                                Header(label = stringResource(id = R.string.lbl_msr))
                                Spacer(modifier = Modifier.height(12.dp))
                                MSRComposable(labelId = R.string.lbl_os, value = it.os)
                                MSRComposable(labelId = R.string.lbl_memory, value = it.memory)
                                MSRComposable(labelId = R.string.lbl_storage, value = it.storage)
                                MSRComposable(labelId = R.string.lbl_graphics, value = it.graphics)
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                        Text(
                            text = stringResource(
                                id = R.string.lbl_developer_copyright,
                                gameDetails.developer
                            ),
                            fontSize = 11.sp,
                            color = Gray400,
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        PlayTheGameButton(
                            onPlayTheGameClicked = {
                                uriHandler.openUri(gameDetails.gameUrl)
                            }
                        )
                    }
                }
            }
        }
    }
}
