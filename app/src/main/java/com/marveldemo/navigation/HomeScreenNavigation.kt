package com.marveldemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.marveldemo.presentation.common.BottomAppBarItem

internal const val homeGraphRoute = "home"

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(
        startDestination = marvelHomeRoute,
        route = homeGraphRoute
    ) {
        pokemonListScreen(navController)
        pokemonDetailsScreen(navController)
        menuListScreen(navController)
    }
}

fun NavController.navigateSingleTopWithPopUpTo(
    item: BottomAppBarItem
) {
    val (_, navigate) = when (item) {
        BottomAppBarItem.Home -> Pair(marvelHomeRoute, ::navigateToPokemonList)
        BottomAppBarItem.Heroes -> Pair(heroesRoute, ::navigateToMenu)
    }

    val navOptions = navOptions {
        launchSingleTop = true
        restoreState = true
        //popUpTo(route)
    }
    navigate(navOptions)
}

