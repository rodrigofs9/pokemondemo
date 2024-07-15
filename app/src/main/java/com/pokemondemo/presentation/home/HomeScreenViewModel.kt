package com.pokemondemo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemondemo.data.PokemonDao
import com.pokemondemo.domain.model.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = PokemonDao()

    private val _uiState: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = {
                    _uiState.value = _uiState.value.copy(
                        searchText = it,
                        searchedPokemonList = searchedPokemonList(it)
                    )
                }
            )
        }

        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            dao.getPokemonList().collect {
                _uiState.value = _uiState.value.copy(
                    searchedPokemonList = searchedPokemonList(_uiState.value.searchText)
                )
            }
        }
    }

    private fun containsInName(text: String) = { pokemon: Pokemon ->
        pokemon.name.contains(text, ignoreCase = true)
    }

    private fun searchedPokemonList(text: String): List<Pokemon> =
        if (text.isNotBlank()) {
            dao.getPokemonList().value.filter(containsInName(text))
        } else dao.getPokemonList().value
}