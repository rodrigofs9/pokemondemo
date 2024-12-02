package com.marveldemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.marveldemo.presentation.favorites.FavoriteHeroesScreen

internal const val favoriteHeroesRoute = "favoriteHeroes"

fun NavGraphBuilder.favoriteHeroesListScreen(navController: NavHostController) {
    composable(favoriteHeroesRoute) {
        FavoriteHeroesScreen(
            onNavigateToDetails = { details ->
                navController.navigateToDetails(details.name)
            },
        )
    }
}

fun NavController.navigateToFavoriteHeroes(
    navOptions: NavOptions? = null
){
    navigate(favoriteHeroesRoute, navOptions)
}