package com.pokemondemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.pokemondemo.presentation.common.BottomAppBarItem

internal const val homeGraphRoute = "home"

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(
        startDestination = pokemonListRoute,
        route = homeGraphRoute
    ) {
        pokemonListScreen(navController)
        menuListScreen(navController)
    }
}

fun NavController.navigateSingleTopWithPopUpTo(
    item: BottomAppBarItem
) {
    val (route, navigate) = when (item) {
        BottomAppBarItem.Home -> Pair(pokemonListRoute, ::navigateToPokemonList)
        BottomAppBarItem.Menu -> Pair(menuRoute, ::navigateToMenu)
    }

    val navOptions = navOptions {
        launchSingleTop = true
        popUpTo(route)
    }
    navigate(navOptions)
}

