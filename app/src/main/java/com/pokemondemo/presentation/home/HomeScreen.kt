package com.pokemondemo.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pokemondemo.domain.model.Pokemon
import com.pokemondemo.domain.model.PokemonType
import com.pokemondemo.presentation.theme.PokemonDemoTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        var searchText by remember { mutableStateOf("") }
        val pokemonList = listOf(
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
        OutlinedTextField(
            value = searchText,
            onValueChange = { newValue ->
                searchText = newValue
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(100),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Ícone de Busca") },
            label = { Text(text = "Qual pokémon você procura?") },
            placeholder = { Text("Qual pokémon você procura?") })
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(pokemonList) { pokemon ->
                PokemonHomeListItem(
                    pokemon = pokemon,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    PokemonDemoTheme {
        Surface {
            HomeScreen()
        }
    }
}
