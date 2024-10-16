package com.pokemondemo.presentation

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
import com.pokemondemo.navigation.PokemonNavHost
import com.pokemondemo.navigation.menuRoute
import com.pokemondemo.navigation.navigateSingleTopWithPopUpTo
import com.pokemondemo.navigation.pokemonListRoute
import com.pokemondemo.presentation.common.BottomAppBar
import com.pokemondemo.presentation.common.BottomAppBarItem
import com.pokemondemo.presentation.common.bottomAppBarItems
import com.pokemondemo.presentation.home.HomeScreen
import com.pokemondemo.presentation.home.HomeScreenState
import com.pokemondemo.presentation.theme.PokemonDemoTheme

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
                    pokemonListRoute -> BottomAppBarItem.Home
                    menuRoute -> BottomAppBarItem.Menu
                    else -> BottomAppBarItem.Home
                }
                mutableStateOf(item)
            }
            val containsInBottomAppBarItems = when(currentRoute) {
                pokemonListRoute, menuRoute -> true
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
                PokemonNavHost(navController)
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
    PokemonDemoTheme {
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
    App {
        HomeScreen(HomeScreenState())
    }
}