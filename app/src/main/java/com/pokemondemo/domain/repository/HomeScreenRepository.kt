package com.pokemondemo.domain.repository

import com.pokemondemo.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface HomeScreenRepository {
    fun getPokemonList(query: String): Flow<List<Pokemon>>
}