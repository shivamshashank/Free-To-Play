package com.brighterbee.tech.freetoplay.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.brighterbee.tech.freetoplay.presentation.all_games.AllGamesScreen
import com.brighterbee.tech.freetoplay.presentation.game_details.GameDetailsScreen

@Composable
fun Navigation(
    innerPadding: PaddingValues,
    scaffoldState: ScaffoldState,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Screen.AllGamesScreen.route,
    ) {
        composable(Screen.AllGamesScreen.route) {
            AllGamesScreen(scaffoldState, navController)
        }
        composable(
            Screen.GameDetailsScreen.route + "/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.IntType }),
        ) {
            GameDetailsScreen(navController)
        }
    }
}