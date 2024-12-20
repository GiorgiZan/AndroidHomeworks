package com.example.androidhomeworks

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidhomeworks.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayUserInfo()
        goToAddUserActivity()
        newUser()
        addDescToUser()
    }


    private fun displayUserInfo() {
        val users = UsersList.users
        binding.btnSearch.setOnClickListener {
            if (binding.etUserInfoField.text.toString().isEmpty()) {
                binding.etUserInfoField.error = "Search Field should not be empty!"
                clearFields()
                return@setOnClickListener
            }
            val userInfoSearchField = binding.etUserInfoField.text.toString().lowercase()
            val userBirthday = binding.etUserInfoField.text.toString()
            val foundUser = users.find {
                val fullName = "${it.firstName} ${it.lastName}".lowercase()
                fullName == userInfoSearchField || convertTimestampToDate(
                    it.birthday.lowercase()
                ) == userBirthday || it.email.lowercase() == userInfoSearchField || it.desc?.lowercase() == userInfoSearchField || it.address.lowercase() == userInfoSearchField
            }

            if (foundUser == null) {
                Snackbar.make(binding.root, "User not found!", Snackbar.LENGTH_SHORT).show()
                binding.btnGoToAddNewUser.visibility = View.VISIBLE
                clearFields()
                return@setOnClickListener
            }

            binding.tvId.text = "id :" + foundUser?.id.toString()
            binding.tvFirstName.text = "First Name: " + foundUser?.firstName.toString()
            binding.tvLastName.text = "Last Name: " + foundUser?.lastName.toString()
            binding.tvBirthday.text = "Birthday: " + convertTimestampToDate(foundUser?.birthday.toString())
            binding.tvAddress.text = "Address: " + foundUser?.address.toString()
            binding.tvEmail.text = "Email: " + foundUser?.email.toString()
            binding.tvDesc.text = "desc: " + foundUser?.desc.toString()
        }
    }

    private fun addDescToUser() {
        binding.btnAddDescription.setOnClickListener {
            if (binding.etIdToUpdate.text.toString().isEmpty()) {
                binding.etIdToUpdate.error = "Id should not be empty"
                return@setOnClickListener
            }
            if (binding.etIdToUpdate.text.toString().isEmpty()) {
                binding.etDescToUpdate.error = "Desc should not be empty"
            }
            val user = UsersList.users.find { it.id == binding.etIdToUpdate.text.toString().toInt() }

            user?.apply { desc = binding.etDescToUpdate.text.toString()}
        }

    }

    private fun convertTimestampToDate(timestamp: String): String {
        val timestampLong = timestamp.toLong()
        val date = Date(timestampLong)
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(date)
    }

    private fun goToAddUserActivity() {
        binding.btnGoToAddNewUser.setOnClickListener() {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clearFields() {
        binding.tvId.text = ""
        binding.tvFirstName.text = ""
        binding.tvLastName.text = ""
        binding.tvBirthday.text = ""
        binding.tvAddress.text = ""
        binding.tvEmail.text = ""
        binding.tvDesc.text = ""

        binding.etIdToUpdate.text?.clear()
        binding.etDescToUpdate.text?.clear()
    }


    private fun newUser(){
        val id = intent.getIntExtra("id", -1)
        val firstName = intent.getStringExtra("firstName") ?: ""
        val lastName = intent.getStringExtra("lastName") ?: ""
        val birthday = intent.getStringExtra("birthday") ?: ""
        val address = intent.getStringExtra("address") ?: ""
        val email = intent.getStringExtra("email") ?: ""


        binding.tvId.text = id.toString()
        binding.tvFirstName.text = firstName
        binding.tvLastName.text = lastName
        binding.tvBirthday.text = birthday
        binding.tvAddress.text = address
        binding.tvEmail.text = email

        if (id == -1) {
            binding.tvId.text = ""
        }
    }
}





