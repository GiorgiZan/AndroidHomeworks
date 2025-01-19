package com.example.androidhomeworks.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentRegisterBinding
import com.example.androidhomeworks.models.AuthViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val authViewModel: AuthViewModel by viewModels()


    override fun listeners() {
        binding.btnRegister.setOnClickListener {
            if (!validateFields()) {
                return@setOnClickListener
            }
            registerViaService()
        }
    }

    private fun registerViaService() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        authViewModel.register(email, password,
            onSuccess = {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            },
            onError = {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        )
    }


    private fun validateFields(): Boolean {
        if (binding.etEmail.text.toString().isEmpty()) {
            binding.etEmail.error = getString(R.string.email_should_not_be_empty)
            return false
        }
        if (binding.etUsername.text.toString().isEmpty()) {
            binding.etUsername.error = getString(R.string.username_should_not_be_empty)
            return false
        }
        if (binding.etEmail.text.toString().isEmpty()) {
            binding.etEmail.error = getString(R.string.password_should_not_be_empty)
            return false
        }
        return true
    }

}