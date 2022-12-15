package com.brighterbee.tech.freetoplay.data.remote.dto.game_details

import com.brighterbee.tech.freetoplay.domain.model.GameDetails
import com.google.gson.annotations.SerializedName

data class GameDetailsDTO(
    val description: String,
    val developer: String,
    @SerializedName("freetogame_profile_url")
    val freeToGameProfileUrl: String,
    @SerializedName("game_url")
    val gameUrl: String,
    val genre: String,
    val id: Int,
    @SerializedName("minimum_system_requirements")
    val minimumSystemRequirements: MinimumSystemRequirements,
    val platform: String,
    val publisher: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val screenshots: List<Screenshot>,
    @SerializedName("short_description")
    val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String
) {
    fun toGameDetails(): GameDetails {
        return GameDetails(
            description,
            developer,
            freeToGameProfileUrl,
            gameUrl,
            genre,
            id,
            minimumSystemRequirements,
            platform,
            publisher,
            releaseDate,
            screenshots,
            shortDescription,
            status,
            thumbnail,
            title
        )
    }
}