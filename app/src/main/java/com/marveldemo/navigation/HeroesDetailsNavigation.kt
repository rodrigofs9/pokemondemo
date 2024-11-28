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
private const val name = "name"
private const val imageUrl = "imageUrl"

fun NavGraphBuilder.heroesDetailsScreen(navController: NavHostController) {
    composable(
        "$heroesDetailsRoute/{$name}/{$imageUrl}",
        arguments = listOf(
            navArgument("name") {
                type = NavType.StringType
            }, navArgument("imageUrl") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val name = backStackEntry.arguments?.getString(name).orEmpty()
        val imageUrl = backStackEntry.arguments?.getString(imageUrl).orEmpty()

        if (name.isNotEmpty() && imageUrl.isNotEmpty()){
            val viewModel: DetailsScreenViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsState()
            LaunchedEffect(Unit) {
                viewModel.getPokemonDetails(name, imageUrl)
            }
            DetailsScreen(state = uiState)
        } else {
            LaunchedEffect(Unit) {
                navController.navigateUp()
            }
        }
    }
}

fun NavController.navigateToDetails(name: String, imageUrl: String) {
    navigate("$heroesDetailsRoute/$name/$imageUrl")
}