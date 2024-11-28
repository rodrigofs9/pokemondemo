package com.marveldemo.data.cache.datasource

import com.marveldemo.data.cache.dao.HeroesDao
import com.marveldemo.data.cache.mapper.toCacheModel
import com.marveldemo.data.cache.mapper.toDomain
import com.marveldemo.domain.model.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HeroLocalDataSourceImpl(private val heroesDao: HeroesDao) : HeroLocalDataSource {
    override suspend fun getFavorites(): Flow<List<Hero>> = flowOf(
        heroesDao.getAll().map { it.toDomain() }
    )

    override suspend fun getFavoriteById(id: Int): Flow<Hero> = flowOf(
        heroesDao.findById(id = id).toDomain()
    )

    override suspend fun insertFavorite(hero: Hero) =
        heroesDao.insertHero(hero = hero.toCacheModel())

    override suspend fun removeFavorite(hero: Hero) = heroesDao.delete(hero = hero.toCacheModel())
}
