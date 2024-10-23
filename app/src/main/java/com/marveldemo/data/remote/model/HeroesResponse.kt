package com.marveldemo.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroesResponse (
    @SerialName("data")
    val data: DataResponse
){
    @Serializable
    data class DataResponse(
        @SerialName("results")
        val results: List<HeroResponse>
    )

    @Serializable
    data class HeroResponse(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("description")
        val description: String,
        @SerialName("thumbnail")
        val thumbnailResponse: ThumbnailResponse,
    )

    @Serializable
    data class ThumbnailResponse(
        @SerialName("path")
        val path: String,
        @SerialName("extension")
        val extension: String,
    )
}