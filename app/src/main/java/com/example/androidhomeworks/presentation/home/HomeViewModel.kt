package com.example.androidhomeworks.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidhomeworks.data.local.room.user.UserDatabase
import com.example.androidhomeworks.data.local.room.user.UserEntity
import com.example.androidhomeworks.mediator.Mediator
import com.example.androidhomeworks.retrofit.RetrofitClient
import kotlinx.coroutines.flow.Flow


class HomeViewModel(private val database: UserDatabase) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    private val pager= Pager(
        config = PagingConfig(
            pageSize = 6,
            prefetchDistance = 1
        ),
        remoteMediator = Mediator(database, RetrofitClient.retrofitService),
        pagingSourceFactory = { database.userDao().getUsers() }
    )

    val usersFlow: Flow<PagingData<UserEntity>> = pager.flow.cachedIn(viewModelScope)
}
