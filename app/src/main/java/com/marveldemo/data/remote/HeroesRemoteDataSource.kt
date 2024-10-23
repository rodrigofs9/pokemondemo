package com.marveldemo.data.remote

import com.marveldemo.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface HeroesRemoteDataSource {
    suspend fun fetchHeroes(): Flow<List<Hero>>
}