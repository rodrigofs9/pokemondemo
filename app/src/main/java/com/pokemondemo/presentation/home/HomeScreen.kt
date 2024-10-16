package com.pokemondemo.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pokemondemo.navigation.model.DetailsArgs
import com.pokemondemo.presentation.theme.PokemonDemoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    onNavigateToDetails: (DetailsArgs) -> Unit = {},
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    HomeScreen(state = state, onNavigateToDetails = onNavigateToDetails)
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onNavigateToDetails: (DetailsArgs) -> Unit = {},
) {
    Column {
        val text = state.searchText
        val searchedPokemonList = state.searchedPokemonList

        OutlinedTextField(
            value = text,
            onValueChange = state.onSearchChange,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(100),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Ícone de Busca") },
            label = { Text(text = "Qual pokémon você procura?") },
            placeholder = { Text("Qual pokémon você procura?") })
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(searchedPokemonList) { pokemon ->
                PokemonHomeListItem(pokemon = pokemon, modifier = Modifier.clickable {
                    onNavigateToDetails(
                        DetailsArgs(
                            name = pokemon.name,
                            imageUrl = pokemon.imageUrl,
                            description = "descrição"
                        )
                    )
                })
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
