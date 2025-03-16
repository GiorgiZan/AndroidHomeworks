package com.example.androidhomeworks.presentation.extension.lifecyclescope

import android.graphics.Color
import android.view.View
import com.example.androidhomeworks.R
import com.google.android.material.snackbar.Snackbar

fun View.showErrorSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
        setBackgroundTint(resources.getColor(R.color.bright_red, context.theme))
        setBackgroundTint(Color.RED)
        show()
    }
}

fun View.showSuccessSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).apply {
        setBackgroundTint(resources.getColor(R.color.light_green, context.theme))
        setTextColor(Color.BLACK)
        show()
    }
}