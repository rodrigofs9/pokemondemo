package com.marveldemo.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = listOf(viewModelModule, useCaseModule, repositoryModule, daoModule)

        startKoin {
            androidLogger()
            androidContext(this@MarvelDemoApplication)
            modules(appModules)
        }
    }
}