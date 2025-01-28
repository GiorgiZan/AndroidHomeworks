package com.example.androidhomeworks.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androidhomeworks.dto_response.UsersDto
import com.example.androidhomeworks.retrofit.RetrofitService

class UserPagingSource(private val retrofitService: RetrofitService) :
    PagingSource<Int, UsersDto.User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UsersDto.User> {
        val page = params.key ?: 1
        return try {
            val response = retrofitService.getUsers(page)
            val users = response.body()?.data ?: emptyList()
            LoadResult.Page(
                data = users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (users.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UsersDto.User>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}
