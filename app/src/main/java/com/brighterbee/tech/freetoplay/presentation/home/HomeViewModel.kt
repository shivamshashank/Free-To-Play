package com.brighterbee.tech.freetoplay.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brighterbee.tech.freetoplay.common.Resource
import com.brighterbee.tech.freetoplay.domain.use_case.GetGamesByPlatformUseCase
import com.brighterbee.tech.freetoplay.domain.use_case.SortGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGamesByPlatformUseCase: GetGamesByPlatformUseCase,
    private val sortGamesUseCase: SortGamesUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        savedStateHandle.get<String>("platform")?.let { platform ->
            if (platform == "release-date") {
                sortGames(platform)
            } else {
                getGames(platform)
            }
        }
    }

    private fun getGames(platform: String) {
        getGamesByPlatformUseCase(platform).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = HomeState(allGamesList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        HomeState(error = result.message ?: "An unexpected error occurred!")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun sortGames(platform: String) {
        sortGamesUseCase(platform).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = HomeState(allGamesList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        HomeState(error = result.message ?: "An unexpected error occurred!")
                }
            }
        }.launchIn(viewModelScope)
    }

}