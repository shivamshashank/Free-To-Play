package com.brighterbee.tech.freetoplay.presentation.home

import com.brighterbee.tech.freetoplay.domain.model.Game

data class HomeState(
    val isLoading: Boolean = false,
    val allGamesList: List<Game>? = null,
    val error: String = "",
)
