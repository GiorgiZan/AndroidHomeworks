package com.example.androidhomeworks.mapper

import com.example.androidhomeworks.data.presenter.PostPresenter
import com.example.androidhomeworks.data.remote.posts.PostsDto
import com.example.androidhomeworks.utils.toFormattedDate



fun PostsDto.toPresenter(): PostPresenter {
    return PostPresenter(
        id = id,
        images = images ?: emptyList(),
        title = title,
        comments = comments.toString().plus(" ").plus("Comments"),
        likes = likes.toString().plus(" ").plus("Likes"),
        shareContent = shareContent,
        ownerFullName = owner.firstName.plus(" ").plus(owner.lastName),
        ownerProfile = owner.profile,
        formattedDate = owner.postDate.toFormattedDate()
    )
}
