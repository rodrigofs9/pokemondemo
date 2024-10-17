package com.pokemondemo.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pokemondemo.presentation.details.DetailsScreen
import com.pokemondemo.presentation.details.DetailsScreenViewModel
import org.koin.androidx.compose.koinViewModel

private const val pokemonDetailsRoute = "pokemonDetails"
private const val userId = "userId"

fun NavGraphBuilder.pokemonDetailsScreen(navController: NavHostController) {
    composable(
        "$pokemonDetailsRoute/{$userId}",
        arguments = listOf(navArgument("userId") {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        backStackEntry.arguments?.getString(userId)?.let { id ->
            val viewModel: DetailsScreenViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsState()
            LaunchedEffect(Unit) {
                viewModel.getPokemonDetails(id)
            }
            DetailsScreen(state = uiState)
        } ?: LaunchedEffect(Unit) {
            navController.navigateUp()
        }
    }
}

fun NavController.navigateToDetails(id: String) {
    navigate("$pokemonDetailsRoute/$id")
}