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
        val id: Int? = null,
        @SerialName("name")
        val name: String? = null,
        @SerialName("description")
        val description: String? = null,
        @SerialName("thumbnail")
        val thumbnail: ThumbnailResponse? = null,
    )

    @Serializable
    data class ThumbnailResponse(
        @SerialName("path")
        val path: String? = null,
        @SerialName("extension")
        val extension: String? = null,
    )
}