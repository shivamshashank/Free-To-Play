package com.brighterbee.tech.freetoplay.data.remote

import com.brighterbee.tech.freetoplay.data.remote.dto.game.GameDTO
import com.brighterbee.tech.freetoplay.data.remote.dto.game_details.GameDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeToGameApi {
    @GET("games")
    suspend fun getGamesByPlatform(
        @Query("platform") platform: String,
    ): List<GameDTO>

    @GET("games")
    suspend fun sortGames(
        @Query("sort-by") sortBy: String,
    ): List<GameDTO>

    @GET("game")
    suspend fun getSingleGames(
        @Query("id") id: Int,
    ): GameDetailsDTO?
}