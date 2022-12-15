package com.brighterbee.tech.freetoplay.domain.use_case

import com.brighterbee.tech.freetoplay.common.Resource
import com.brighterbee.tech.freetoplay.domain.model.GameDetails
import com.brighterbee.tech.freetoplay.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSingleGameUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    operator fun invoke(id: Int): Flow<Resource<GameDetails>> = flow {
        try {
            emit(Resource.Loading<GameDetails>())
            val getSingleGameDetailsDTO = gameRepository.getSingleGame(id)

            if (getSingleGameDetailsDTO == null) {
                emit(Resource.Error<GameDetails>("Game Details is Null"))
            } else {
                val getSingleGameDetails = getSingleGameDetailsDTO.toGameDetails()
                emit(Resource.Success<GameDetails>(getSingleGameDetails))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<GameDetails>(e.localizedMessage ?: "An unexpected error occurred!"))
        } catch (e: IOException) {
            emit(Resource.Error<GameDetails>("You are offline!"))
        }
    }
}