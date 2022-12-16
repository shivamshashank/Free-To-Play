package com.brighterbee.tech.freetoplay.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.brighterbee.tech.freetoplay.presentation.game_details.GameDetailsScreen
import com.brighterbee.tech.freetoplay.presentation.home.HomeScreen

@Composable
fun Navigation(
    innerPadding: PaddingValues,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Screen.HomeScreen.route + "/{platform}",
    ) {
        composable(
            Screen.HomeScreen.route + "/{platform}",
            arguments = listOf(navArgument("platform") {
                type = NavType.StringType
                defaultValue = "all"
            }),
        ) {
            HomeScreen(scaffoldState, navController)
        }
        composable(
            Screen.GameDetailsScreen.route + "/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.IntType }),
        ) {
            GameDetailsScreen(navController)
        }
    }
}