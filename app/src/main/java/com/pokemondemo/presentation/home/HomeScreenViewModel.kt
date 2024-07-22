package com.pokemondemo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemondemo.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) :
    ViewModel() {

    private val _uiState: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = { query ->
                    _uiState.update { it.copy(searchText = query) }
                    getPokemonList(query)
                }
            )
        }

        getPokemonList(query = _uiState.value.searchText)
    }

    private fun getPokemonList(query: String) {
        viewModelScope.launch {
            getPokemonListUseCase(query = query).collect { list ->
                _uiState.update { currentState ->
                    currentState.copy(searchedPokemonList = list)
                }
            }
        }
    }
}