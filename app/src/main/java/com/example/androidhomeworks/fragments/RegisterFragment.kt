package com.example.androidhomeworks.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentRegisterBinding
import com.example.androidhomeworks.models.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()


    override fun listeners() {
        registerStateManagement()

        binding.btnRegister.setOnClickListener {
            if (!validateFields()) {
                return@setOnClickListener
            }
            registerViaService()

        }
        binding.ivBackIcon.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun registerStateManagement() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.stateManagement.collectLatest { state ->
                    if (state.isLoading) {
                        loading()
                    } else {
                        loaded()
                    }

                    state.successMessage?.let {
                        val result = Bundle().apply {
                            loaded()
                            putString("email", binding.etEmail.text.toString())
                            putString("password", binding.etPassword.text.toString())
                        }
                        setFragmentResult("requestKey", result)
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                        navigateToLogin()
                    }

                    state.errorMessage?.let {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun registerViaService() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        registerViewModel.register(email, password)

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

        if (binding.etRepeatPassword.text.toString().isEmpty()) {
            binding.etRepeatPassword.error = getString(R.string.password_should_not_be_empty)
            return false
        }
        if (binding.etRepeatPassword.text.toString() != binding.etPassword.text.toString()) {
            Snackbar.make(
                binding.root,
                getString(R.string.passwords_should_be_equal), Snackbar.LENGTH_SHORT
            ).show()
            return false
        }
        return true
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