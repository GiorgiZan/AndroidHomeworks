package com.example.androidhomeworks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var usersCount: Int = 0
    private val users = mutableMapOf<String,String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addUser()
        getUserInfo()



    }

    private fun addUser(){
        binding.btnAddUser.setOnClickListener{
            if (binding.etFullName.text!!.isEmpty()){
                binding.etFullName.error = resources.getString(R.string.full_name_empty)
                return@setOnClickListener
            }
            else if(binding.etFullName.text!!.length < 7 || binding.etFullName.text!!.length > 30){
                binding.etFullName.error = resources.getString(R.string.full_name_size_not_valid)
                return@setOnClickListener
            }
            if (binding.etEmail.text!!.isEmpty()){
                binding.etEmail.error = resources.getString(R.string.email_empty)
                return@setOnClickListener
            }
            else if (!binding.etEmail.text!!.contains("@")){
                binding.etEmail.error = resources.getString(R.string.email_not_valid)
                return@setOnClickListener
            }
            else if (binding.etEmail.text!!.length < 7 || binding.etEmail.text!!.length > 35) {
                binding.etEmail.error = resources.getString(R.string.email_length_not_valid)
                return@setOnClickListener
            }
            else if (users.containsKey(binding.etEmail.text!!.toString().lowercase())){
                binding.etEmail.error = resources.getString(R.string.email_already_exists)
                return@setOnClickListener
            }

            users[binding.etEmail.text.toString().lowercase()] = binding.etFullName.text.toString()
            binding.tvUserInfo.text = ""
            usersCount++
            binding.tvUsersCount.text = "Users -> $usersCount"
        }
    }

    private fun getUserInfo(){
        binding.btnGetUserInfo.setOnClickListener{
            if (binding.etFindUser.text!!.isEmpty()){
                binding.etFindUser.error = resources.getString(R.string.email_empty)
                return@setOnClickListener
            }
            else if (!binding.etFindUser.text!!.contains("@")){
                binding.etFindUser.error = resources.getString(R.string.email_not_valid)
                return@setOnClickListener
            }
            else if (binding.etFindUser.text!!.length < 7 || binding.etFindUser.text!!.length > 35) {
                binding.etFindUser.error = resources.getString(R.string.email_length_not_valid)
                return@setOnClickListener
            }
            else if (!users.containsKey(binding.etFindUser.text!!.toString().lowercase())){
                binding.tvUserInfo.text = resources.getString(R.string.user_not_found)
                return@setOnClickListener
            }
            binding.tvUserInfo.text = "Full Name: ${users[binding.etFindUser.text.toString().lowercase()]}\nEmail: ${binding.etFindUser.text.toString().lowercase()} "
        }
    }



}
