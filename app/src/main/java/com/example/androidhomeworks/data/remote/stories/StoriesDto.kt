package com.example.androidhomeworks.data.remote.stories

import kotlinx.serialization.Serializable

@Serializable
data class StoriesDto(
    val id: Int,
    val cover: String,
    val title: String
)