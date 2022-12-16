package com.brighterbee.tech.freetoplay.domain.use_case

import com.brighterbee.tech.freetoplay.common.Resource
import com.brighterbee.tech.freetoplay.domain.model.Game
import com.brighterbee.tech.freetoplay.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SortGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {

    operator fun invoke(sortBy: String): Flow<Resource<List<Game>>> = flow {
        try {
            emit(Resource.Loading<List<Game>>())
            val getAllGamesList = gameRepository.sortGames(sortBy).map { it.toGame() }
            emit(Resource.Success<List<Game>>(getAllGamesList))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Game>>(e.localizedMessage ?: "An unexpected error occurred!"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Game>>("You are offline!"))
        }
    }

}