package com.example.androidhomeworks

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomeworks.databinding.ActivityAddUserBinding
import com.google.android.material.snackbar.Snackbar

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
                binding.etId.error = getString(R.string.id_empty)
                return@setOnClickListener
            }
            if (binding.etFirstName.text.toString().isEmpty()){
                binding.etFirstName.error = getString(R.string.first_empty)
                return@setOnClickListener

            }
            if (binding.etLastName.text.toString().isEmpty()){
                binding.etLastName.error = getString(R.string.last_empty)
                return@setOnClickListener
            }
            if (binding.etBirthday.text.toString().isEmpty()){
                binding.etBirthday.error = getString(R.string.birthday_empty)
                return@setOnClickListener
            }
            if (binding.etAddress.text.toString().isEmpty()){
                binding.etAddress.error = getString(R.string.address_empty)
                return@setOnClickListener
            }
            if (binding.etEmail.text.toString().isEmpty()){
                binding.etEmail.error = getString(R.string.email_empty)
                return@setOnClickListener
            }
            val id = binding.etId.text.toString().trim().toInt()
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val formattedDate = binding.etBirthday.text.toString().trim()
            val address = binding.etAddress.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val userDesc = intent.getStringExtra("desc")

            val user = UsersList.users.find { it.id == id }

            if (user != null){
                Snackbar.make(binding.root,
                    getString(R.string.user_already_exists), Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            UsersList.users.add(User( id = id,
                firstName = firstName,
                lastName = lastName,
                birthday = formattedDate,
                address = address,
                email = email,
                desc = userDesc))

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("id", id)
                putExtra("firstName", firstName)
                putExtra("lastName", lastName)
                putExtra("birthday", HelperMethods.convertTimestampToDate(formattedDate))
                putExtra("address", address)
                putExtra("email", email)
                putExtra("desc", userDesc)
            }

            setResult(RESULT_OK,intent)
            finish()
        }


    }





}