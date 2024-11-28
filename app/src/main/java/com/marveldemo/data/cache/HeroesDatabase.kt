package com.marveldemo.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marveldemo.data.cache.dao.HeroesDao
import com.marveldemo.data.cache.entity.HeroCM

@Database(entities = [HeroCM::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heroesDao(): HeroesDao
}
