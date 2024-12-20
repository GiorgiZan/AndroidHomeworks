package com.example.androidhomeworks

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidhomeworks.databinding.ActivityAddUserBinding
import com.example.androidhomeworks.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addUser()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addUser(){
        binding.btnSaveUser.setOnClickListener{
            if (binding.etId.text.toString().isEmpty()){
                binding.etId.error = "Id filed should not be empty"
                return@setOnClickListener
            }
            if (binding.etFirstName.text.toString().isEmpty()){
                binding.etFirstName.error ="First name should not be empty"
                return@setOnClickListener

            }
            if (binding.etLastName.text.toString().isEmpty()){
                binding.etLastName.error ="Last name should not be empty"
                return@setOnClickListener
            }
            if (binding.etBirthday.text.toString().isEmpty()){
                binding.etBirthday.error ="Birthday should not be empty"
                return@setOnClickListener
            }
            if (binding.etAddress.text.toString().isEmpty()){
                binding.etAddress.error ="Address should not be empty"
                return@setOnClickListener
            }
            if (binding.etEmail.text.toString().isEmpty()){
                binding.etEmail.error ="Email should not be empty"
                return@setOnClickListener
            }
            val id = binding.etId.text.toString().trim().toInt()
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val formattedDate = binding.etBirthday.text.toString().trim()
            val address = binding.etAddress.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            val user = UsersList.users.find { it.id == id }

            if (user != null){
                Snackbar.make(binding.root, "User with that id already exists", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            UsersList.users.add(User( id = id,
                firstName = firstName,
                lastName = lastName,
                birthday = formattedDate,
                address = address,
                email = email))

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("id", id)
                putExtra("firstName", firstName)
                putExtra("lastName", lastName)
                putExtra("birthday", convertTimestampToDate(formattedDate))
                putExtra("address", address)
                putExtra("email", email)
            }

            startActivity(intent)
        }


    }
    private fun convertTimestampToDate(timestamp: String): String {
        val timestampLong = timestamp.toLong()
        val date = Date(timestampLong)
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(date)
    }




}