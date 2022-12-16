package com.brighterbee.tech.freetoplay.presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object GameDetailsScreen : Screen("game_details_screen")
}