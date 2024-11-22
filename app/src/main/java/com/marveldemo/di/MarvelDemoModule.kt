package com.marveldemo.di

import com.marveldemo.data.PokemonDao
import com.marveldemo.data.remote.HeroesRemoteDataSourceImpl
import com.marveldemo.data.remote.retrofit.RetrofitClient
import com.marveldemo.data.remote.service.MarvelService
import com.marveldemo.data.repository.HeroRepositoryImpl
import com.marveldemo.data.repository.HomeScreenRepositoryImpl
import com.marveldemo.domain.repository.HeroRepository
import com.marveldemo.domain.repository.HomeScreenRepository
import com.marveldemo.domain.usecase.GetHeroesUseCase
import com.marveldemo.domain.usecase.GetPokemonListUseCase
import com.marveldemo.presentation.details.DetailsScreenViewModel
import com.marveldemo.presentation.heroes.HeroesScreenViewModel
import com.marveldemo.presentation.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel {
        HomeScreenViewModel(
            getPokemonListUseCase = GetPokemonListUseCase(homeScreenRepository = get())
        )
    }
    viewModel { DetailsScreenViewModel() }
    viewModel { HeroesScreenViewModel(getHeroesUseCase = GetHeroesUseCase(heroRepository = get())) }
}

val useCaseModule = module {
    factory { GetPokemonListUseCase(homeScreenRepository = get()) }
    factory { GetHeroesUseCase(heroRepository = get()) }
}

val repositoryModule = module {
    single<HomeScreenRepository> { HomeScreenRepositoryImpl(dao = get()) }
    single<HeroRepository> {
        HeroRepositoryImpl(
            heroesRemoteDataSource = HeroesRemoteDataSourceImpl(
                marvelService = get<Retrofit>().create(MarvelService::class.java)
            )
        )
    }
}

val daoModule = module {
    single { PokemonDao() }
}

val networkModule = module {
    single<Retrofit> { RetrofitClient().create() }
}