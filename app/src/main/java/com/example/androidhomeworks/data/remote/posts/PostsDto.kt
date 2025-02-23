package com.example.androidhomeworks.data.remote.posts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class PostsDto(
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    @SerialName("share_content") val shareContent: String,
    val owner: PostOwnerDto
){
    @Serializable
    data class PostOwnerDto(
        @SerialName("first_name") val firstName: String?,
        @SerialName("last_name") val lastName: String?,
        @SerialName("profile") val profile: String?,
        @SerialName("post_date") val postDate: Long
    )
}
