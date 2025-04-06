package com.example.androidhomeworks.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidhomeworks.R
import com.example.androidhomeworks.presentation.theme.AppTheme
import com.example.androidhomeworks.presentation.util.textFieldColors

@Composable
fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        placeholder = {
            Text(label)
        },
        visualTransformation = if (isPassword && !passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisibility) stringResource(R.string.hide_password) else stringResource(
                            R.string.show_password
                        )
                    )
                }
            }
        } else null,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon, contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(height = 27.dp, width = 27.dp)
            )
        },
        colors = textFieldColors(),
        modifier = modifier.fillMaxWidth()
    )
}
@Preview(showBackground = true)
@Composable
fun AuthTextFieldPreview() {
    AppTheme {
        TextFieldComponent(
            value = "",
            onValueChange = {},
            isPassword = true,
            label = "Email",
            leadingIcon = Icons.Default.Abc
        )
    }
}


