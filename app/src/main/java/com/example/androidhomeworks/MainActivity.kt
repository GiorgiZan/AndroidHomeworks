package com.example.androidhomeworks

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userFieldsValidation: UserFieldsValidation
    private var listOfUsers = mutableListOf<UserInfo>()
    private var countOfDeletedUsers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        userFieldsValidation = UserFieldsValidation(applicationContext)
        setContentView(binding.root)

        activeUsersCount()
        deletedUsersCount()
        listeners()
    }

    private fun activeUsersCount(){
        binding.tvActiveUsersCount.text = getString(R.string.active_users, listOfUsers.size)
    }

    private fun deletedUsersCount(){
        binding.tvDeletedUsersCount.text = getString(R.string.deleted_users, countOfDeletedUsers)
    }

    private fun listeners(){
        addUserListener()
        updateUserListener()
        deleteUserListener()
    }

    private fun addUserListener(){
        binding.btnAddUser.setOnClickListener{
            if (!validateAllField()){
                return@setOnClickListener
            }
            if (listOfUsers.any{it.email == binding.etEmail.text.toString().lowercase().trim()}){
                Toast.makeText(this, getString(R.string.user_with_that_email_exists), Toast.LENGTH_SHORT).show()
                errorStatus()
                return@setOnClickListener
            }

            listOfUsers.add(UserInfo(binding.etFirstName.text.toString().trim(),binding.etLastName.text.toString().trim(),binding.etAge.text.toString().trim().toInt(),binding.etEmail.text.toString().lowercase().trim()))
            activeUsersCount()
            successStatus()
            clearAllFields()
        }
    }

    private fun updateUserListener(){
        binding.btnUpdateUser.setOnClickListener{
            if (listOfUsers.none{it.email == binding.etEmail.text.toString().lowercase().trim()}){
                Toast.makeText(this,
                    getString(R.string.user_not_found_update_error), Toast.LENGTH_SHORT).show()
                errorStatus()
                return@setOnClickListener
            }
            if (!validateAllField()){
                return@setOnClickListener
            }

            val userToUpdate = listOfUsers.find { it.email == binding.etEmail.text.toString().lowercase().trim() }

            userToUpdate?.apply {
                if (this.firstName == binding.etFirstName.text.toString() && this.lastName == binding.etLastName.text.toString() && this.age == binding.etAge.text.toString().toInt()){
                    Toast.makeText(applicationContext,
                        getString(R.string.changing_nothing_when_updating),Toast.LENGTH_SHORT).show()
                    binding.tvOperationStatus.text = ""
                    return@setOnClickListener
                }
                else{
                    this.firstName = binding.etFirstName.text.toString()
                    this.lastName = binding.etLastName.text.toString()
                    this.age = binding.etAge.text.toString().toInt()
                    Toast.makeText(applicationContext, getString(R.string.user_successfully_updated), Toast.LENGTH_SHORT).show()
                }
            }

            successStatus()
            clearAllFields()
        }
    }

    private fun deleteUserListener(){
        binding.btnDeleteUser.setOnClickListener{
            if (listOfUsers.none{it.email == binding.etEmail.text.toString().lowercase().trim()}){
                Toast.makeText(this,
                    getString(R.string.user_not_found_delete_error), Toast.LENGTH_SHORT).show()
                errorStatus()
                return@setOnClickListener
            }

            val userToDelete = listOfUsers.find { it.email == binding.etEmail.text.toString().lowercase().trim() }

            listOfUsers.remove(userToDelete)

            successStatus()
            countOfDeletedUsers++
            deletedUsersCount()
            clearAllFields()
            activeUsersCount()
        }
    }



    private fun errorStatus(){
        binding.tvOperationStatus.text = getString(R.string.error_status)
        binding.tvOperationStatus.setTextColor(Color.RED)
    }

    private fun successStatus(){
        binding.tvOperationStatus.text = getString(R.string.success_status)
        binding.tvOperationStatus.setTextColor(Color.GREEN)
    }

    private fun validateAllField(): Boolean {
        if (!userFieldsValidation.userFirstNameValidation(binding.etFirstName)){
            errorStatus()
            return false
        }
        if (!userFieldsValidation.userLastNameValidation(binding.etLastName)){
            errorStatus()
            return false
        }

        if (!userFieldsValidation.userAgeValidation(binding.etAge)){
            errorStatus()
            return false
        }

        if (!userFieldsValidation.userEmailValidations(binding.etEmail)){
            errorStatus()
            return false
        }

        return true
    }

    private fun clearAllFields(){
        binding.etFirstName.text!!.clear()
        binding.etLastName.text!!.clear()
        binding.etAge.text!!.clear()
        binding.etEmail.text!!.clear()
    }




}


