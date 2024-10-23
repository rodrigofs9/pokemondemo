package com.marveldemo.domain.repository

import com.marveldemo.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface HeroRepository {
    suspend fun fetchHeroes(): Flow<List<Hero>>
}