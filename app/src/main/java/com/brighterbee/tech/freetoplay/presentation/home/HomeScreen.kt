package com.brighterbee.tech.freetoplay.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.brighterbee.tech.freetoplay.R
import com.brighterbee.tech.freetoplay.domain.model.Game
import com.brighterbee.tech.freetoplay.presentation.components.CarouselView
import com.brighterbee.tech.freetoplay.presentation.components.ErrorScreen
import com.brighterbee.tech.freetoplay.presentation.home.components.GameCard
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    val state = viewModel.state.value

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "Menu Icon Button",
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_magnifying_glass_solid),
                        contentDescription = "Search Icon Button",
                    )
                }
            }

            if (state.allGamesList != null) {
                if (state.allGamesList.isEmpty()) {
                    ErrorScreen(message = stringResource(id = R.string.txt_empty_result))
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(count = 2),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            val randomGame: List<Game> = state.allGamesList.asSequence().shuffled()
                                .take(state.allGamesList.size.coerceAtMost(3)).toList()

                            val urlsList: MutableList<String> = arrayListOf()

                            randomGame.forEach {
                                urlsList.add(it.thumbnail)
                            }
                            CarouselView(
                                urlsList,
                                1.0f,
                            )
                        }
                        items(items = state.allGamesList) { game ->
                            GameCard(
                                game,
                                navController,
                            )
                        }
                    }
                }
            }
        }
    }
}