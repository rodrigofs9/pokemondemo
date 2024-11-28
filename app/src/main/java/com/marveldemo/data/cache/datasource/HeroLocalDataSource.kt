package com.marveldemo.data.cache.datasource

import com.marveldemo.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface HeroLocalDataSource {
    suspend fun getFavorites(): Flow<List<Hero>>
    suspend fun getFavoriteById(id: Int): Flow<Hero>
    suspend fun insertFavorite(hero: Hero)
    suspend fun removeFavorite(hero: Hero)
}