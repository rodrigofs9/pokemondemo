package com.pokemondemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pokemondemo.presentation.details.DetailsScreen

private const val pokemonDetailsRoute = "pokemonDetails"
private const val idArgument = "idArgument"

fun NavGraphBuilder.pokemonDetailsScreen(navController: NavHostController) {
    composable(
        "$pokemonDetailsRoute/{$idArgument}"
    ) { backStackEntry ->
        //val id = backStackEntry.arguments?.getParcelable(productIdArgument)
        DetailsScreen()
    }
}

fun NavController.navigateToDetails(id: String){
    navigate("$pokemonDetailsRoute/$id")
}