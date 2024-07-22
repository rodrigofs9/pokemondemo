package com.pokemondemo.data.repository

import com.pokemondemo.data.PokemonDao
import com.pokemondemo.domain.model.Pokemon
import com.pokemondemo.domain.repository.HomeScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeScreenRepositoryImpl(private val dao: PokemonDao) : HomeScreenRepository {
    private fun containsInName(text: String) = { pokemon: Pokemon ->
        pokemon.name.contains(text, ignoreCase = true)
    }

    override fun getPokemonList(query: String): Flow<List<Pokemon>> {
        return dao.getPokemonList().map { pokemonList ->
            if (query.isNotBlank()) pokemonList.filter(containsInName(query)) else pokemonList
        }
    }
}