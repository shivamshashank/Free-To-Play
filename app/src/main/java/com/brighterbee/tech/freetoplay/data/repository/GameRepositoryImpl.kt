package com.brighterbee.tech.freetoplay.data.repository

import com.brighterbee.tech.freetoplay.data.remote.FreeToGameApi
import com.brighterbee.tech.freetoplay.data.remote.dto.game.GameDTO
import com.brighterbee.tech.freetoplay.data.remote.dto.game_details.GameDetailsDTO
import com.brighterbee.tech.freetoplay.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val freeToGameApi: FreeToGameApi,
) : GameRepository {
    override suspend fun getSingleGame(id: Int): GameDetailsDTO? = freeToGameApi.getSingleGames(id)

    override suspend fun getGamesByPlatform(platform: String): List<GameDTO> =
        freeToGameApi.getGamesByPlatform(platform)

    override suspend fun sortGames(sortBy: String): List<GameDTO> = freeToGameApi.sortGames(sortBy)
}