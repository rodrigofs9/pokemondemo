package com.marveldemo.presentation.home

import com.marveldemo.domain.model.Pokemon

data class HomeScreenState (
    val searchedPokemonList: List<Pokemon> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
)