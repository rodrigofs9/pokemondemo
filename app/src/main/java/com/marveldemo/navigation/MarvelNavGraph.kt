package com.marveldemo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.marveldemo.presentation.common.BottomAppBarItem

internal const val homeGraphRoute = "home"

fun NavGraphBuilder.marvelGraph(navController: NavHostController) {
    navigation(
        startDestination = marvelHeroesRoute,
        route = homeGraphRoute
    ) {
        heroesListScreen(navController)
        heroesDetailsScreen(navController)
        favoriteHeroesListScreen(navController)
    }
}

fun NavController.navigateSingleTopWithPopUpTo(
    item: BottomAppBarItem
) {
    val (_, navigate) = when (item) {
        BottomAppBarItem.Heroes -> Pair(marvelHeroesRoute, ::navigateToHeroesList)
        BottomAppBarItem.Favorites -> Pair(favoriteHeroesRoute, ::navigateToFavoriteHeroes)
    }

    val navOptions = navOptions {
        launchSingleTop = true
        restoreState = true
        //popUpTo(route)
    }
    navigate(navOptions)
}

