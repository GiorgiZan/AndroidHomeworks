package com.example.androidhomeworks.presentation.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomeworks.databinding.ActivityMainBinding
import com.example.androidhomeworks.presentation.navigation.AppNavGraph
import com.example.androidhomeworks.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppNavGraph()
            }
        }
    }
}