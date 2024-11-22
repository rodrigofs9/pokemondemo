package com.marveldemo.presentation.heroes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marveldemo.navigation.model.DetailsArgs
import com.marveldemo.presentation.theme.HeroDemoTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HeroesScreen(
    onNavigateToDetails: (DetailsArgs) -> Unit = {},
    viewModel: HeroesScreenViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val listState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() }
    HeroesScreen(state = state, onNavigateToDetails = onNavigateToDetails, listState = listState)
}

@Composable
fun HeroesScreen(
    state: HeroesScreenState,
    listState: LazyListState,
    onNavigateToDetails: (DetailsArgs) -> Unit = {},
) {
    Column {
        val text = state.searchText
        val heroesList = state.heroesList

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = Color.Blue
                )
            }
        } else {
            OutlinedTextField(
                value = text,
                onValueChange = state.onSearchChange,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(100),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Ícone de Busca") },
                label = { Text(text = "Qual herói você procura?") },
                placeholder = { Text("Qual herói você procura?") })
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(heroesList) { hero ->
                    HeroesListItem(hero = hero, modifier = Modifier.clickable {
                        onNavigateToDetails(
                            DetailsArgs(
                                name = hero.name,
                                imageUrl = hero.imageUrl,
                                description = hero.description
                            )
                        )
                    })
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HeroesScreenPreview() {
    HeroDemoTheme {
        Surface {
            HeroesScreen()
        }
    }
}
