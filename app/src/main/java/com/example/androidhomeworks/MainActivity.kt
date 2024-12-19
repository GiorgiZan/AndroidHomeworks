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
        setup()
        backButton()
    }

    private fun setup() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, MainFragment(), "MainFragment")
            .commit()
    }


    private fun backButton(){
        binding.ivBackIcon.setOnClickListener{
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            }
            else{
                finish()
            }
        }
    }


}