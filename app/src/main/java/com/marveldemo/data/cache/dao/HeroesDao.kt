package com.marveldemo.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.marveldemo.data.cache.entity.HeroCM

@Dao
interface HeroesDao {
    @Query("SELECT * FROM table_heroes")
    fun getAll(): List<HeroCM>

    @Query("SELECT * FROM table_heroes WHERE id IN (:heroIds)")
    fun loadAllByIds(heroIds: IntArray): List<HeroCM>

    @Query("SELECT * FROM table_heroes WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): HeroCM

    @Insert
    fun insertAll(vararg heroes: HeroCM)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHero(hero: HeroCM)

    @Delete
    fun delete(hero: HeroCM)
}