package com.example.androidhomeworks.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidhomeworks.R
import com.example.androidhomeworks.presentation.components.BackIcon
import com.example.androidhomeworks.presentation.components.CollectSideEffect

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()


    CollectSideEffect(viewModel.uiEffect) {effect ->
        when(effect){
            ProfileUiEffect.OnNavigateToLogin ->  onNavigateToLogin()
        }
    }

    ProfileContent(
        email = state.email,
        onBackClick = onNavigateBack,
        onEvent = viewModel::onEvent,
    )
}

@Composable
fun ProfileContent(
    email: String,
    onBackClick: () -> Unit,
    onEvent: (ProfileUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BackIcon(
            onBack = onBackClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Email:",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = email,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {onEvent(ProfileUiEvent.OnSignOutClick)},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.log_out))
        }
    }
}
