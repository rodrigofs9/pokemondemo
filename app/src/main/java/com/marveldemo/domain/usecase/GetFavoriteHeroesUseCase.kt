package com.marveldemo.domain.usecase

import com.marveldemo.domain.model.Hero
import com.marveldemo.domain.repository.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavoriteHeroesUseCase(private val heroRepository: HeroRepository) {
    suspend operator fun invoke(): Flow<List<Hero>> =
        heroRepository.getFavorites()
}