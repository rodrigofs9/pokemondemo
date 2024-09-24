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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pokemondemo.navigation.AppDestination
import com.pokemondemo.navigation.bottomAppBarItems
import com.pokemondemo.presentation.common.BottomAppBar
import com.pokemondemo.presentation.common.BottomAppBarItem
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
                    val routes = navController.backQueue.map {
                        it.destination.route
                    }
                    Log.i("MainActivity", "onCreate: back stack - $routes")
                }
            }
            val backStackEntryState by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntryState?.destination

            val selectedItem by remember(currentDestination) {
                val item = currentDestination?.let { destination ->
                    bottomAppBarItems.find {
                        it.destination.route == destination.route
                    }
                } ?: bottomAppBarItems.first()
                mutableStateOf(item)
            }
            val containsInBottomAppBarItems = currentDestination?.let { destination ->
                bottomAppBarItems.find {
                    it.destination.route == destination.route
                }
            } != null

            App(
                bottomAppBarItemSelected = selectedItem,
                onBottomAppBarItemSelectedChange = {
                    val route = it.destination.route
                    navController.navigate(route) {
                        launchSingleTop = true
                        popUpTo(route)
                    }
                },
                showTopBar = containsInBottomAppBarItems,
                showBottomBar = containsInBottomAppBarItems,
            ) {
                NavHost(
                    navController = navController,
                    startDestination = AppDestination.Home.route
                ) {
                    composable(AppDestination.Home.route) {
                        HomeScreen()
                    }
                    composable(AppDestination.Menu.route) {
                        HomeScreen()
                    }
                }
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