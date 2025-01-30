package com.example.androidhomeworks.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentUserBinding
import com.example.androidhomeworks.models.UserViewModel
import com.example.androidhomeworks.models.ViewModelFactory
import com.example.androidhomeworks.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class UserFragment : BaseFragment<FragmentUserBinding>(FragmentUserBinding::inflate) {

    private val protoDataStore by lazy { UserRepository(requireContext()) }

    private val userViewModel: UserViewModel by viewModels {
        ViewModelFactory { UserViewModel(protoDataStore) }
    }

    override fun listeners() {
        binding.btnSave.setOnClickListener {
            if (!validateField()) {
                return@setOnClickListener
            }
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.etEmail.text.toString()
            userViewModel.saveUser(firstName, lastName, email)
            clearTextFields()
            Snackbar.make(binding.root, getString(R.string.user_saved), Snackbar.LENGTH_SHORT)
                .show()
        }
        binding.btnRead.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    val user = userViewModel.userPreferencesFlow.first()
                    binding.tvFirstName.text = user.firstName
                    binding.tvLastName.text = user.lastName
                    binding.tvEmail.text = user.email
                }
            }

        }
    }


    private fun validateField(): Boolean {
        if (binding.etFirstName.text.toString().isEmpty()) {
            binding.etFirstName.error = getString(R.string.firstname_should_not_be_empty)
            return false
        }
        if (binding.etLastName.text.toString().isEmpty()) {
            binding.etLastName.error = getString(R.string.lastname_should_not_be_empty)
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString())
                .matches()
        ) {
            binding.etEmail.error = getString(R.string.enter_valid_email)
            return false
        }
        if (binding.etEmail.text.toString().isEmpty()) {
            binding.etEmail.error = getString(R.string.email_should_not_be_empty)
            return false
        }

        return true
    }

    private fun clearTextFields(){
        binding.tvFirstName.text = ""
        binding.tvLastName.text = ""
        binding.tvEmail.text = ""
    }
}