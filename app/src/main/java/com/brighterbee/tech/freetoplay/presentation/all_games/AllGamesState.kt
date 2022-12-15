package com.brighterbee.tech.freetoplay.presentation.all_games

import com.brighterbee.tech.freetoplay.domain.model.Game

data class AllGamesState(
    val isLoading: Boolean = false,
    val allGamesList: List<Game>? = null,
    val error: String = "",
)
