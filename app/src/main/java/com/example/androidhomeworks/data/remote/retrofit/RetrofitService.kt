package com.example.androidhomeworks.data.remote.retrofit

import com.example.androidhomeworks.data.remote.posts.PostsDto
import com.example.androidhomeworks.data.remote.stories.StoriesDto
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("00a18030-a8c7-47c4-b0c5-8bff92a29ebf")
    suspend fun getStories(): Response<List<StoriesDto>>

    @GET("1ba8b612-8391-41e5-8560-98e4a48decc7")
    suspend fun getPosts(): Response<List<PostsDto>>
}