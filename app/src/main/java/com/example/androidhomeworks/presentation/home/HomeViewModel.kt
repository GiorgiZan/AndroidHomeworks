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
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val database: UserDatabase, private val retrofitService: RetrofitService) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    private val pager= Pager(
        config = PagingConfig(
            pageSize = 6,
            prefetchDistance = 1
        ),
        remoteMediator = Mediator(database, retrofitService),
        pagingSourceFactory = { database.userDao().getUsers() }
    )

    val usersFlow: Flow<PagingData<UserEntity>> = pager.flow.cachedIn(viewModelScope)
}
