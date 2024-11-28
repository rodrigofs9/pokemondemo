package com.marveldemo.presentation.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
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
fun FavoriteHeroesScreen(
    onNavigateToDetails: (DetailsArgs) -> Unit = {},
    viewModel: FavoriteHeroesScreenViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val listState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() }
    FavoriteHeroesScreen(
        state = state,
        onNavigateToDetails = onNavigateToDetails,
        listState = listState
    )
}

@Composable
fun FavoriteHeroesScreen(
    state: FavoriteHeroesScreenState,
    listState: LazyListState,
    onNavigateToDetails: (DetailsArgs) -> Unit = {},
) {
    Column {
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
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(heroesList) { hero ->
                    FavoriteHeroesListItem(hero = hero, modifier = Modifier.clickable {
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
private fun FavoriteHeroesScreenPreview() {
    HeroDemoTheme {
        Surface {
            FavoriteHeroesScreen()
        }
    }
}
