package com.example.androidhomeworks.data.repository

import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.data.remote.posts.PostsDto
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.data.remote.stories.StoriesDto
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val retrofitService: RetrofitService
) {
    suspend fun fetchStories(): Resource<List<StoriesDto>>{
        return apiHelper.handleHttpRequest (apiCall = {
            retrofitService.getStories()
        } )
    }

    suspend fun fetchPosts(): Resource<List<PostsDto>>{
        return apiHelper.handleHttpRequest  (apiCall = {
            retrofitService.getPosts()
        })
    }
}