package com.brighterbee.tech.freetoplay.domain.repository

import com.brighterbee.tech.freetoplay.data.remote.dto.game.GameDTO
import com.brighterbee.tech.freetoplay.data.remote.dto.game_details.GameDetailsDTO

interface GameRepository {
    suspend fun getAllGames(): List<GameDTO>
    suspend fun getSingleGame(id: Int): GameDetailsDTO?
}