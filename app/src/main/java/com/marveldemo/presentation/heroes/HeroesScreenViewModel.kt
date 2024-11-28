package com.marveldemo.presentation.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marveldemo.domain.usecase.GetHeroesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HeroesScreenViewModel(private val getHeroesUseCase: GetHeroesUseCase) :
    ViewModel() {

    private val _uiState: MutableStateFlow<HeroesScreenState> = MutableStateFlow(
        HeroesScreenState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = { query ->
                    _uiState.update { it.copy(searchText = query) }
                    getHeroesList(query)
                }
            )
        }

        getHeroesList(query = _uiState.value.searchText)
    }

    private fun getHeroesList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }
            try {
                getHeroesUseCase(query = query).collect { list ->
                    _uiState.update { currentState ->
                        currentState.copy(heroesList = list, isLoading = false)
                    }
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(isLoading = false, heroesList = emptyList())
                }
            }
        }
    }
}