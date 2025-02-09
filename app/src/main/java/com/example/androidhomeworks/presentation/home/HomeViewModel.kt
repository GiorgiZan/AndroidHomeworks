package com.example.androidhomeworks.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidhomeworks.adapters.UserPagingSource
import com.example.androidhomeworks.data.remote.users.UsersResponseDto
import com.example.androidhomeworks.retrofit.RetrofitClient
import kotlinx.coroutines.flow.Flow

class HomeViewModel : ViewModel() {

    val usersFlow: Flow<PagingData<UsersResponseDto.User>> = Pager(
        config = PagingConfig(
            pageSize = 6,
            prefetchDistance = 1
        ),
        pagingSourceFactory = { UserPagingSource(RetrofitClient.retrofitService) }
    ).flow.cachedIn(viewModelScope)

}
