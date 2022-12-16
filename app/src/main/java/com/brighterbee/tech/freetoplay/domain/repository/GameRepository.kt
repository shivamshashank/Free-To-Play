package com.brighterbee.tech.freetoplay.domain.repository

import com.brighterbee.tech.freetoplay.data.remote.dto.game.GameDTO
import com.brighterbee.tech.freetoplay.data.remote.dto.game_details.GameDetailsDTO

interface GameRepository {
    suspend fun getSingleGame(id: Int): GameDetailsDTO?
    suspend fun getGamesByPlatform(platform: String): List<GameDTO>
    suspend fun sortGames(sortBy: String): List<GameDTO>
}