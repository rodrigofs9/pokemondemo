package com.pokemondemo.di

import com.pokemondemo.presentation.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeScreenViewModel() }
}