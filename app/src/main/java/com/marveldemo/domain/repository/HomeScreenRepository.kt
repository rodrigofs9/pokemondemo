package com.marveldemo.domain.repository

import com.marveldemo.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface HomeScreenRepository {
    fun getPokemonList(query: String): Flow<List<Pokemon>>
}