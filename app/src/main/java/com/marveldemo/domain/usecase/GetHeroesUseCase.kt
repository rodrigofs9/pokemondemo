package com.marveldemo.domain.usecase

import com.marveldemo.domain.model.Hero
import com.marveldemo.domain.repository.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHeroesUseCase(private val heroRepository: HeroRepository) {
    suspend operator fun invoke(query: String): Flow<List<Hero>> =
        heroRepository.fetchHeroes()
            .map { heroesList ->
                heroesList.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            }
}