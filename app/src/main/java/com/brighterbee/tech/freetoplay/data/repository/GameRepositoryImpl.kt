package com.brighterbee.tech.freetoplay.data.repository

import com.brighterbee.tech.freetoplay.data.remote.FreeToGameApi
import com.brighterbee.tech.freetoplay.data.remote.dto.game.GameDTO
import com.brighterbee.tech.freetoplay.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val freeToGameApi: FreeToGameApi,
) : GameRepository {
    override suspend fun getAllGames(): List<GameDTO> {
        return freeToGameApi.getAllGames()
    }
}