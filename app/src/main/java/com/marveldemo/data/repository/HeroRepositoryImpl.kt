package com.marveldemo.data.repository

import com.marveldemo.data.cache.datasource.HeroLocalDataSource
import com.marveldemo.data.remote.HeroesRemoteDataSource
import com.marveldemo.domain.model.Hero
import com.marveldemo.domain.repository.HeroRepository
import kotlinx.coroutines.flow.Flow

class HeroRepositoryImpl(
    private val heroesRemoteDataSource: HeroesRemoteDataSource,
    private val heroLocalDataSource: HeroLocalDataSource
) : HeroRepository {
    override suspend fun fetchHeroes(): Flow<List<Hero>> =
        heroesRemoteDataSource.fetchHeroes()

    override suspend fun getFavorites(): Flow<List<Hero>> =
        heroLocalDataSource.getFavorites()

    override suspend fun getFavoriteById(id: Int): Flow<Hero> =
        heroLocalDataSource.getFavoriteById(id = id)

    override suspend fun insertFavorite(hero: Hero) =
        heroLocalDataSource.insertFavorite(hero = hero)

    override suspend fun removeFavorite(hero: Hero) =
        heroLocalDataSource.removeFavorite(hero = hero)
}