package com.example.androidhomeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        supportFragmentManager.beginTransaction().replace(R.id.main, ChatFragment(), "ChatFragment").addToBackStack("ChatFragment")
            .commit()
    }
}