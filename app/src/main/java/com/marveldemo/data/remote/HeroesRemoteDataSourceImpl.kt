package com.marveldemo.data.remote

import com.marveldemo.data.remote.model.HeroesResponse
import com.marveldemo.data.remote.service.MarvelService
import com.marveldemo.domain.model.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HeroesRemoteDataSourceImpl(private val marvelService: MarvelService) :
    HeroesRemoteDataSource {
    override suspend fun fetchHeroes(): Flow<List<Hero>> =
        flowOf(marvelService.getHeroes().data.results.map { it.toDomain() })
}

fun HeroesResponse.HeroResponse.toDomain() = Hero(
    id = id,
    name = name,
    description = description,
    imageUrl = thumbnailResponse.path + "." + thumbnailResponse.extension
)