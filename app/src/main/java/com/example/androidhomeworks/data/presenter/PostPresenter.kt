package com.example.androidhomeworks.data.presenter

data class PostPresenter(
    val id: Int,
    val images: List<String>,
    val title: String,
    val comments: String,
    val likes: String,
    val shareContent: String,
    val ownerFullName: String,
    val ownerProfile : String?,
    val formattedDate: String
)

