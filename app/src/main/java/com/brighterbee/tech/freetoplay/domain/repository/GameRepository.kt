package com.brighterbee.tech.freetoplay.domain.repository

import com.brighterbee.tech.freetoplay.data.remote.dto.game.GameDTO

interface GameRepository {
    suspend fun getAllGames() : List<GameDTO>
}