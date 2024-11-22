package com.marveldemo.data.remote.service

import com.marveldemo.data.remote.model.HeroesResponse
import retrofit2.http.GET

interface MarvelService {
    @GET("v1/public/characters?limit=10")
    suspend fun getHeroes(): HeroesResponse

    //@GET("v1/public/characters/{heroId}")
    //suspend fun getHeroById(@Path("heroId") heroId: Int): HeroesResponse
}