package com.marveldemo.data.cache.mapper

import com.marveldemo.data.cache.entity.HeroCM
import com.marveldemo.domain.model.Hero

fun HeroCM.toDomain() = Hero(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun Hero.toCacheModel() = HeroCM(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl
)