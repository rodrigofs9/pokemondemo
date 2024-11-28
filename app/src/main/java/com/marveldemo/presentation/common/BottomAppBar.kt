package com.marveldemo.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.marveldemo.presentation.theme.HeroDemoTheme

sealed class BottomAppBarItem(
    val label: String,
    val icon: ImageVector
){
    data object Heroes : BottomAppBarItem(
        label = "Heroes",
        icon = Icons.Filled.Home
    )

    data object Favorites : BottomAppBarItem(
        label = "Favorites",
        icon = Icons.Filled.Star
    )
}

val bottomAppBarItems = listOf(BottomAppBarItem.Heroes, BottomAppBarItem.Favorites)

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
    HeroDemoTheme {
        BottomAppBar(
            item = bottomAppBarItems.first(),
            items = bottomAppBarItems
        )
    }
}