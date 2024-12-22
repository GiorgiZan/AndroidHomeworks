package com.example.androidhomeworks

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhomeworks.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val addUser = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        binding.tvId.text =
            getString(R.string.display_id, result.data?.getIntExtra("id", -1).toString())
        binding.tvFirstName.text =
            getString(R.string.display_first_name, result.data?.getStringExtra("firstName"))
        binding.tvLastName.text =
            getString(R.string.display_last_name,  result.data?.getStringExtra("lastName"))
        binding.tvBirthday.text =
            getString(R.string.display_birthday,  result.data?.getStringExtra("birthday"))
        binding.tvAddress.text =
            getString(R.string.display_address,  result.data?.getStringExtra("address"))
        binding.tvEmail.text =
            getString(R.string.display_email,  result.data?.getStringExtra("email"))
        binding.tvDesc.text =
            getString(R.string.display_desc,  result.data?.getStringExtra("desc"))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayUserInfo()
        goToAddUserActivity()
    }


    private fun displayUserInfo() {
        val users = UsersList.users
        binding.btnSearch.setOnClickListener {
            if (binding.etUserInfoField.text.toString().isEmpty()) {
                binding.etUserInfoField.error = getString(R.string.search_empty)
                clearFields()
                return@setOnClickListener
            }
            val userInfoSearchField = binding.etUserInfoField.text.toString().lowercase()
            val userBirthday = binding.etUserInfoField.text.toString()
            val foundUser = users.find {
                val fullName = "${it.firstName} ${it.lastName}".lowercase()
                fullName == userInfoSearchField || HelperMethods.convertTimestampToDate(
                    it.birthday.lowercase()
                ) == userBirthday || it.email.lowercase() == userInfoSearchField || it.desc?.lowercase() == userInfoSearchField || it.address.lowercase() == userInfoSearchField
            }

            if (foundUser == null) {
                Snackbar.make(binding.root,
                    getString(R.string.user_not_found), Snackbar.LENGTH_SHORT).show()
                binding.btnGoToAddNewUser.visibility = View.VISIBLE
                clearFields()
                return@setOnClickListener
            }

            binding.tvId.text =
                getString(R.string.display_id, foundUser.id.toString())
            binding.tvFirstName.text =
                getString(R.string.display_first_name, foundUser.firstName)
            binding.tvLastName.text =
                getString(R.string.display_last_name, foundUser.lastName)
            binding.tvBirthday.text =
                getString(R.string.display_birthday, HelperMethods.convertTimestampToDate(foundUser.birthday))
            binding.tvAddress.text =
                getString(R.string.display_address, foundUser.address)
            binding.tvEmail.text =
                getString(R.string.display_email, foundUser.email)
            binding.tvDesc.text =
                getString(R.string.display_desc, foundUser.desc.toString())
        }
    }



    private fun goToAddUserActivity() {
        binding.btnGoToAddNewUser.setOnClickListener() {
            val intent = Intent(this, AddUserActivity::class.java)
            intent.putExtra("desc", binding.etUserInfoField.text.toString() )
            addUser.launch(intent)
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
    }

}





