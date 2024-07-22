package com.pokemondemo.domain.usecase

import com.pokemondemo.domain.model.Pokemon
import com.pokemondemo.domain.repository.HomeScreenRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val homeScreenRepository: HomeScreenRepository) {
    operator fun invoke(
        query: String
    ): Flow<List<Pokemon>> = homeScreenRepository.getPokemonList(query = query)
}