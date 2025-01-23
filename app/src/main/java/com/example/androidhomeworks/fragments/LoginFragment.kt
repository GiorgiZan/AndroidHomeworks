package com.example.androidhomeworks.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentLoginBinding
import com.example.androidhomeworks.models.LoginViewModel
import com.google.android.material.snackbar.Snackbar


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loggedInNavigateToHome()
    }

    private fun loggedInNavigateToHome() {
        sharedPreferences =
            requireContext().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToHome()
        }

    }


    override fun listeners() {
        loadEmailAndPasswordFromRegistration()
        binding.btnLogin.setOnClickListener {
            if (!validateFields()) {
                return@setOnClickListener
            }
            loginViaService()
        }

        binding.btnRegister.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun loginViaService() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val rememberMe = binding.cbRememberMe.isChecked

        loading()

        loginViewModel.login(email, password,
            onSuccess = {
                loaded()
                if (rememberMe) {
                    sharedPreferences.edit()
                        .putBoolean("isLoggedIn", true)
                        .apply()
                }
                sharedPreferences.edit()
                    .putString("email", email)
                    .apply()
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                navigateToHome()
            },
            onError = {
                loaded()
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        )
    }


    private fun validateFields(): Boolean {
        if (binding.etEmail.text.toString().isEmpty()) {
            binding.etEmail.error = getString(R.string.email_should_not_be_empty)
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString())
                .matches()
        ) {
            binding.etEmail.error = getString(R.string.invalid_email_address)
            return false
        }
        if (binding.etPassword.text.toString().isEmpty()) {
            binding.etPassword.error = getString(R.string.password_should_not_be_empty)
            return false
        }
        return true
    }

    private fun navigateToHome() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment(),
            NavOptions.Builder()
                .setPopUpTo(R.id.loginFragment, true)
                .build()
        )
    }

    private fun navigateToRegister() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }

    private fun loadEmailAndPasswordFromRegistration() {
        parentFragmentManager.setFragmentResultListener(
            "requestKey",
            viewLifecycleOwner
        ) { _, bundle ->
            val email = bundle.getString("email")
            val password = bundle.getString("password")

            binding.etEmail.setText(email)
            binding.etPassword.setText(password)
        }
    }

    private fun loading() {
        binding.loading.visibility = View.VISIBLE
        binding.cbRememberMe.isEnabled = false
        binding.btnLogin.isEnabled = false
        binding.btnRegister.isEnabled = false

    }

    private fun loaded() {
        binding.loading.visibility = View.GONE
        binding.cbRememberMe.isEnabled = true
        binding.btnLogin.isEnabled = true
        binding.btnRegister.isEnabled = true
    }
}