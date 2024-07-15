package com.pokemondemo.data

import com.pokemondemo.domain.model.Pokemon
import com.pokemondemo.domain.model.PokemonType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonDao {
    companion object {
        private val products = MutableStateFlow(
            listOf(
                Pokemon(
                    id = "001",
                    name = "Bulbasaur",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                    types = listOf(PokemonType(name = "Grass"), PokemonType(name = "Poison"))
                ),
                Pokemon(
                    id = "007",
                    name = "Squirtle",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png",
                    types = listOf(PokemonType(name = "Water"))
                ),
                Pokemon(
                    id = "004",
                    name = "Charmander",
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png",
                    types = listOf(PokemonType(name = "Fire"))
                ),
            )
        )
    }

    fun getPokemonList(): StateFlow<List<Pokemon>> = products.asStateFlow()

    fun save(product: Pokemon) {
        products.value += product
    }
}
