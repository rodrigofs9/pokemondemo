package com.marveldemo.presentation.favorites

import com.marveldemo.domain.model.Hero

data class FavoriteHeroesScreenState (
    val isLoading: Boolean = false,
    val heroesList: List<Hero> = emptyList(),
)