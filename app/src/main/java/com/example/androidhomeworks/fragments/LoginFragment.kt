package com.example.androidhomeworks.fragments


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentLoginBinding
import com.example.androidhomeworks.models.AuthViewModel
import com.google.android.material.snackbar.Snackbar


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val authViewModel: AuthViewModel by viewModels()


    override fun listeners() {
        binding.btnLogin.setOnClickListener {
            if (!validateFields()) {
                return@setOnClickListener
            }
            loginViaService()
        }
    }

    private fun loginViaService() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        authViewModel.login(email, password,
            onSuccess = {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
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
        if (binding.etPassword.text.toString().isEmpty()) {
            binding.etPassword.error = getString(R.string.password_should_not_be_empty)
            return false
        }
        return true
    }
}