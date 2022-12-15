package com.brighterbee.tech.freetoplay.presentation.game_details

import com.brighterbee.tech.freetoplay.domain.model.GameDetails

data class GameDetailsState(
    val isLoading: Boolean = false,
    val gameDetails: GameDetails? = null,
    val error: String = "",
)
