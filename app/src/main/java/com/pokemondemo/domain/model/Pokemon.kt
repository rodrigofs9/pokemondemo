package com.pokemondemo.domain.model

data class Pokemon(
    val name: String,
    val id: String,
    val types: List<PokemonType>,
    val imageUrl: String,
)