package com.marveldemo.domain.usecase

import com.marveldemo.domain.model.Pokemon
import com.marveldemo.domain.repository.HomeScreenRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val homeScreenRepository: HomeScreenRepository) {
    operator fun invoke(
        query: String
    ): Flow<List<Pokemon>> = homeScreenRepository.getPokemonList(query = query)
}