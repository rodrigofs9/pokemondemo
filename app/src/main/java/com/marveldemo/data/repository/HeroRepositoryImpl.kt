package com.marveldemo.data.repository

import com.marveldemo.data.remote.HeroesRemoteDataSource
import com.marveldemo.domain.model.Hero
import com.marveldemo.domain.repository.HeroRepository
import kotlinx.coroutines.flow.Flow

class HeroRepositoryImpl(private val heroesRemoteDataSource: HeroesRemoteDataSource) :
    HeroRepository {
    override suspend fun fetchHeroes(): Flow<List<Hero>> =
        heroesRemoteDataSource.fetchHeroes()
}