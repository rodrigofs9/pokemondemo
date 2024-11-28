package com.marveldemo.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.marveldemo.navigation.MarvelNavHost
import com.marveldemo.navigation.favoriteHeroesRoute
import com.marveldemo.navigation.marvelHeroesRoute
import com.marveldemo.navigation.navigateSingleTopWithPopUpTo
import com.marveldemo.presentation.common.BottomAppBar
import com.marveldemo.presentation.common.BottomAppBarItem
import com.marveldemo.presentation.common.bottomAppBarItems
import com.marveldemo.presentation.home.HomeScreen
import com.marveldemo.presentation.home.HomeScreenState
import com.marveldemo.presentation.theme.HeroDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            LaunchedEffect(Unit) {
                navController.addOnDestinationChangedListener { _, _, _ ->
                    val routes = navController.currentBackStack.value.map {
                        it.destination.route
                    }
                    Log.i("MainActivity", "onCreate: back stack - $routes")
                }
            }
            val backStackEntryState by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntryState?.destination
            val currentRoute = currentDestination?.route
            val selectedItem by remember(currentDestination) {
                val item = when (currentRoute) {
                    marvelHeroesRoute -> BottomAppBarItem.Heroes
                    favoriteHeroesRoute -> BottomAppBarItem.Favorites
                    else -> BottomAppBarItem.Heroes
                }
                mutableStateOf(item)
            }
            val containsInBottomAppBarItems = when(currentRoute) {
                marvelHeroesRoute, favoriteHeroesRoute -> true
                else -> false
            }

            App(
                bottomAppBarItemSelected = selectedItem,
                onBottomAppBarItemSelectedChange = { item ->
                    navController.navigateSingleTopWithPopUpTo(item)
                },
                showTopBar = containsInBottomAppBarItems,
                showBottomBar = containsInBottomAppBarItems,
            ) {
                MarvelNavHost(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    bottomAppBarItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    showTopBar: Boolean = false,
    showBottomBar: Boolean = false,
    content: @Composable () -> Unit = {},
) {
    HeroDemoTheme {
        Surface {
            Scaffold(
                topBar = {
                    if (showTopBar) {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(text = "Pokémon Demo")
                            },
                        )
                    }
                },
                bottomBar = {
                    if (showBottomBar) {
                        BottomAppBar(
                            item = bottomAppBarItemSelected,
                            items = bottomAppBarItems,
                            onItemChange = onBottomAppBarItemSelectedChange,
                        )
                    }
                },

            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App { HomeScreen(HomeScreenState()) }
}