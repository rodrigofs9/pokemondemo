package com.pokemondemo.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.pokemondemo.presentation.theme.PokemonDemoTheme

sealed class BottomAppBarItem(
    val label: String,
    val icon: ImageVector
){
    data object Home : BottomAppBarItem(
        label = "Home",
        icon = Icons.Filled.Home
    )

    data object Menu : BottomAppBarItem(
        label = "Menu",
        icon = Icons.Filled.Menu
    )
}

val bottomAppBarItems = listOf(BottomAppBarItem.Home, BottomAppBarItem.Menu)

@Composable
fun BottomAppBar(
    item: BottomAppBarItem,
    modifier: Modifier = Modifier,
    items: List<BottomAppBarItem> = emptyList(),
    onItemChange: (BottomAppBarItem) -> Unit = {}
) {
    NavigationBar(modifier) {
        items.forEach {
            val label = it.label
            val icon = it.icon
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) },
                selected = item.label == label,
                onClick = { onItemChange(it) }
            )
        }
    }
}

@Preview
@Composable
fun BottomAppBarPreview() {
    PokemonDemoTheme {
        BottomAppBar(
            item = bottomAppBarItems.first(),
            items = bottomAppBarItems
        )
    }
}