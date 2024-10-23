package com.marveldemo.navigation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsArgs(
    val name: String = "",
    val imageUrl: String = "",
    val description: String = ""
) : Parcelable