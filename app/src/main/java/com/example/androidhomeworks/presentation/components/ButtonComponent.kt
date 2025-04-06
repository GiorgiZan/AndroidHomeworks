package com.example.androidhomeworks.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ButtonComponent(
    onClick: () -> Unit,
    enabled: Boolean,
    text: String
) {
    Button(
        onClick = {
            onClick()
        },
        enabled = enabled,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text)
    }
}

@Preview
@Composable
fun ButtonPreview(){
    ButtonComponent(
        onClick = {},
        enabled = true,
        "fwa"
    )
}