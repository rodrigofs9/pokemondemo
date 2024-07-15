package com.pokemondemo.presentation.home

import com.pokemondemo.domain.model.Pokemon

data class HomeScreenState (
    val searchedPokemonList: List<Pokemon> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
)