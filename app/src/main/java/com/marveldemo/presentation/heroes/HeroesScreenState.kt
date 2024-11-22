package com.marveldemo.presentation.heroes

import com.marveldemo.domain.model.Hero

data class HeroesScreenState (
    val isLoading: Boolean = false,
    val heroesList: List<Hero> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
)