package com.pokemondemo.di

import com.pokemondemo.data.PokemonDao
import com.pokemondemo.data.repository.HomeScreenRepositoryImpl
import com.pokemondemo.domain.repository.HomeScreenRepository
import com.pokemondemo.domain.usecase.GetPokemonListUseCase
import com.pokemondemo.presentation.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeScreenViewModel(
            getPokemonListUseCase = GetPokemonListUseCase(
                homeScreenRepository = get()
            )
        )
    }
}

val useCaseModule = module {
    factory {
        GetPokemonListUseCase(
            homeScreenRepository = get()
        )
    }
}

val repositoryModule = module {
    single<HomeScreenRepository> {
        HomeScreenRepositoryImpl(
            dao = get()
        )
    }
}

val daoModule = module {
    single {
        PokemonDao()
    }
}