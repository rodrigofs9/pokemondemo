package com.marveldemo.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.marveldemo.presentation.details.DetailsScreen
import com.marveldemo.presentation.details.DetailsScreenViewModel
import org.koin.androidx.compose.koinViewModel

private const val heroesDetailsRoute = "heroesDetails"
private const val ARG_NAME = "name"

fun NavGraphBuilder.heroesDetailsScreen(navController: NavHostController) {
    composable(
        "$heroesDetailsRoute/{$ARG_NAME}",
        arguments = listOf(
            navArgument(ARG_NAME) { type = NavType.StringType },
        )
    ) { backStackEntry ->
        val name = backStackEntry.arguments?.getString(ARG_NAME).orEmpty()

        if (name.isNotEmpty()){
            val viewModel: DetailsScreenViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsState()
            LaunchedEffect(Unit) {
                viewModel.getPokemonDetails(name)
            }
            DetailsScreen(state = uiState, onFavoriteClick = { viewModel.toggleFavorite() })
        } else {
            LaunchedEffect(Unit) {
                navController.navigateUp()
            }
        }
    }
}

fun NavController.navigateToDetails(name: String) {
    navigate("$heroesDetailsRoute/$name")
}