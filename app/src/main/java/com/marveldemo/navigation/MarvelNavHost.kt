package com.marveldemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MarvelNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = homeGraphRoute
    ) {
        marvelGraph(navController)
        favoriteHeroesListScreen(navController)
        heroesDetailsScreen(navController)
    }
}