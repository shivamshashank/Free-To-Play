package com.brighterbee.tech.freetoplay.presentation.all_games

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brighterbee.tech.freetoplay.common.Resource
import com.brighterbee.tech.freetoplay.domain.use_case.GetAllGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AllGamesViewModel @Inject constructor(
    private val getAllGamesUseCase: GetAllGamesUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(AllGamesState())
    val state: State<AllGamesState> = _state

    init {
        getAllGames()
    }

    private fun getAllGames() {
        getAllGamesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = AllGamesState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = AllGamesState(allGamesList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        AllGamesState(error = result.message ?: "An unexpected error occurred!")
                }
            }
        }.launchIn(viewModelScope)
    }

}