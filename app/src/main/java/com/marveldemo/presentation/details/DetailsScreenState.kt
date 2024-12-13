package com.marveldemo.presentation.details

data class DetailsScreenState (
    val name: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val isFavorite: Boolean = false
)