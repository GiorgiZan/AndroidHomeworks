package com.example.androidhomeworks.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.androidhomeworks.data.local.room.user.UserEntity
import com.example.androidhomeworks.presentation.components.CollectSideEffect
import com.example.androidhomeworks.presentation.home.components.UserCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToProfile: () -> Unit,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    val users = viewModel.usersFlow.collectAsLazyPagingItems()


    CollectSideEffect(viewModel.uiEffect) { effect ->
        when (effect) {
            HomeUiEffect.NavigateToProfile -> onNavigateToProfile()
            is HomeUiEffect.ShowErrorSnackBar -> snackBarHostState.showSnackbar(effect.message)
        }
    }

    HomeContent(users = users, onEvent = viewModel::onEvent)


}

@Composable
fun HomeContent(
    users: LazyPagingItems<UserEntity>,
    onEvent: (HomeUiEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        when (users.loadState.refresh) {
            is LoadState.Loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 64.dp, bottom = 16.dp)
                ) {
                    items(
                        count = users.itemCount,
                        key = users.itemKey { it.id }
                    ) { index ->
                        users[index]?.let { user ->
                            UserCard(user = user)
                        }
                    }

                    if (users.loadState.append is LoadState.Loading) {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }

                IconButton(
                    onClick = { onEvent(HomeUiEvent.OnProfileClick) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile"
                    )
                }
            }
        }
    }
}
