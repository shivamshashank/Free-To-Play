package com.brighterbee.tech.freetoplay.data.remote

import com.brighterbee.tech.freetoplay.data.remote.dto.game.GameDTO
import retrofit2.http.GET

interface FreeToGameApi {

    @GET("games")
    suspend fun getAllGames(): List<GameDTO>

}