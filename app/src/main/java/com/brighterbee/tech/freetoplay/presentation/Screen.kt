package com.brighterbee.tech.freetoplay.presentation

sealed class Screen(val route: String) {
    object AllGamesScreen : Screen("all_games_screen")
}