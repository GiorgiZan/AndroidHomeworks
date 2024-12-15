package com.example.androidhomeworks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listeners()
    }





    private fun listeners(){
        binding.btnRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java));
        }

        binding.btnLogIn.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java));
        }
    }
}


