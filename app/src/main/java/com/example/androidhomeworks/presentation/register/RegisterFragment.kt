package com.example.androidhomeworks.presentation.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.databinding.FragmentRegisterBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.extension.lifecyclescope.lifecycleCollectLatest
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()
    private var hasNavigatedToLogin = false

    override fun listeners() {

        binding.btnRegister.setOnClickListener {
            observeUiEvents()
            registerViaService()

        }
        binding.ivBackIcon.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun registerViaService() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatedPassword = binding.etRepeatPassword.text.toString()

        registerViewModel.register(email, password, repeatedPassword)

    }

    private fun registerStateManagement() {
        lifecycleCollectLatest(registerViewModel.registerState) { state ->
            when (state) {
                is Resource.Loading -> {
                    loading()
                }

                is Resource.Success -> {
                    if (!hasNavigatedToLogin) {
                        hasNavigatedToLogin = true
                        loaded()
                        val result = Bundle().apply {
                            putString("email", binding.etEmail.text.toString())
                            putString("password", binding.etPassword.text.toString())
                        }
                        setFragmentResult("requestKey", result)

                        Snackbar.make(
                            binding.root,
                            "Register Successful",
                            Snackbar.LENGTH_LONG
                        )
                            .show()
                        navigateToLogin()
                    }
                }

                is Resource.Error -> {
                    loaded()
                    Snackbar.make(binding.root, state.errorMessage, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun observeUiEvents() {
        lifecycleCollectLatest(registerViewModel.uiEvent) { event ->
            when (event) {
                is RegisterUiEvent.ShowEmailError -> {
                    binding.etEmail.error = getString(R.string.invalid_email_address)
                }

                is RegisterUiEvent.ShowPasswordError -> {
                    binding.etPassword.error = getString(R.string.password_should_not_be_empty)
                }

                is RegisterUiEvent.ShowRepeatPasswordError -> {
                    binding.etRepeatPassword.error = getString(R.string.passwords_should_be_equal)
                }

                is RegisterUiEvent.RegisterSuccess -> {
                    registerStateManagement()
                }

            }
        }
    }


    private fun navigateToLogin() {
        findNavController().navigate(
            RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(),
            NavOptions.Builder()
                .setPopUpTo(R.id.registerFragment, true)
                .setLaunchSingleTop(true)
                .build()
        )
    }

    private fun loading() {
        binding.loading.visibility = View.VISIBLE
        binding.btnRegister.isEnabled = false
        binding.ivBackIcon.isEnabled = false

    }

    private fun loaded() {
        binding.loading.visibility = View.GONE
        binding.ivBackIcon.isEnabled = true
        binding.btnRegister.isEnabled = true
    }
}