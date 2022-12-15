package com.brighterbee.tech.freetoplay.presentation.game_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brighterbee.tech.freetoplay.common.Resource
import com.brighterbee.tech.freetoplay.domain.use_case.GetSingleGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val getSingleGameUseCase: GetSingleGameUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(GameDetailsState())
    val state: State<GameDetailsState> = _state

    init {
        savedStateHandle.get<Int>("gameId")?.let { gameId ->
            getSingleGame(id = gameId)
        }
    }

    private fun getSingleGame(id: Int) {
        getSingleGameUseCase(id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = GameDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = GameDetailsState(gameDetails = result.data)
                }
                is Resource.Error -> {
                    _state.value =
                        GameDetailsState(error = result.message ?: "An unexpected error occurred!")
                }
            }
        }.launchIn(viewModelScope)
    }

}