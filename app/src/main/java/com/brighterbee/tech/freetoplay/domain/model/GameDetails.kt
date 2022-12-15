package com.brighterbee.tech.freetoplay.domain.model

import com.brighterbee.tech.freetoplay.data.remote.dto.game_details.MinimumSystemRequirements
import com.brighterbee.tech.freetoplay.data.remote.dto.game_details.Screenshot

data class GameDetails(
    val description: String,
    val developer: String,
    val freeToGameProfileUrl: String,
    val gameUrl: String,
    val genre: String,
    val id: Int,
    val minimumSystemRequirements: MinimumSystemRequirements?,
    val platform: String,
    val publisher: String,
    val releaseDate: String,
    val screenshots: List<Screenshot>,
    val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String
)