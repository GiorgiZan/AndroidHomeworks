package com.example.androidhomeworks.mapper

import com.example.androidhomeworks.data.presenter.StoryPresenter
import com.example.androidhomeworks.data.remote.stories.StoriesDto


fun StoriesDto.toPresenter() : StoryPresenter{
    return StoryPresenter(
        id = id,
        title = title,
        cover = cover
    )
}