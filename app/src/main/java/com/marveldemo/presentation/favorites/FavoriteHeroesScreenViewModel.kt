package com.marveldemo.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marveldemo.domain.usecase.GetFavoriteHeroesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteHeroesScreenViewModel(
    private val getFavoriteHeroesUseCase: GetFavoriteHeroesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<FavoriteHeroesScreenState> = MutableStateFlow(
        FavoriteHeroesScreenState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        getFavoriteHeroesList()
    }

    private fun getFavoriteHeroesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }
            getFavoriteHeroesUseCase().collect { list ->
                _uiState.update { currentState ->
                    currentState.copy(heroesList = list, isLoading = false)
                }
            }
        }
    }
}