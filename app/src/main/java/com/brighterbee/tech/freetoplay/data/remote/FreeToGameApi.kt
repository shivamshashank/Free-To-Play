package com.brighterbee.tech.freetoplay.data.remote

import com.brighterbee.tech.freetoplay.data.remote.dto.game.GameDTO
import com.brighterbee.tech.freetoplay.data.remote.dto.game_details.GameDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeToGameApi {

    @GET("games")
    suspend fun getAllGames(): List<GameDTO>

    @GET("game")
    suspend fun getSingleGames(
        @Query("id") id: Int,
    ): GameDetailsDTO?

}