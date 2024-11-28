package com.marveldemo.domain.usecase

import com.marveldemo.domain.model.Hero
import com.marveldemo.domain.repository.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RemoveFavoriteHeroUseCase(private val heroRepository: HeroRepository) {
    suspend operator fun invoke(hero: Hero) =
        heroRepository.removeFavorite(hero = hero)
}