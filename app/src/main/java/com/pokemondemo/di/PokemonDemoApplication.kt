package com.pokemondemo.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokemonDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = listOf(viewModelModule, useCaseModule, repositoryModule, daoModule)

        startKoin {
            androidLogger()
            androidContext(this@PokemonDemoApplication)
            modules(appModules)
        }
    }
}