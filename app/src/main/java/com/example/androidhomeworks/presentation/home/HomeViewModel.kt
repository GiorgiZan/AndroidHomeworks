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
import com.example.androidhomeworks.presentation.mediator.Mediator
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.presentation.login.LoginUiEffect
import com.example.androidhomeworks.presentation.login.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val database: UserDatabase,
    retrofitService: RetrofitService) : ViewModel() {

    private val _uiEffect = MutableSharedFlow<HomeUiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()


    @OptIn(ExperimentalPagingApi::class)
    val usersFlow: Flow<PagingData<UserEntity>> = Pager(
        config = PagingConfig(
            pageSize = 6,
            prefetchDistance = 1
        ),
        remoteMediator = Mediator(database, retrofitService),
        pagingSourceFactory = { database.userDao().getUsers() }
    ).flow.cachedIn(viewModelScope)

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.OnProfileClick -> {
                viewModelScope.launch {
                    _uiEffect.emit(HomeUiEffect.NavigateToProfile)
                }
            }
        }
    }
}
