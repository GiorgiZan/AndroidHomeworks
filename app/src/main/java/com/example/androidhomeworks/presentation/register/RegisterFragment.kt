package com.example.androidhomeworks.presentation.register

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentRegisterBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.extension.lifecyclescope.lifecycleCollectLatest
import com.example.androidhomeworks.presentation.extension.lifecyclescope.showErrorSnackBar
import com.example.androidhomeworks.presentation.extension.lifecyclescope.showSuccessSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun listeners() {
        binding.btnRegister.setOnClickListener {
            registerViaService()

        }
        binding.ivBackIcon.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun observer() {
        observeRegisterState()
        observeUiEvents()
    }

    private fun registerViaService() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatedPassword = binding.etRepeatPassword.text.toString()

        registerViewModel.register(email, password, repeatedPassword)

    }

    private fun observeRegisterState() {
        lifecycleCollectLatest(registerViewModel.registerState) { state ->
            loading(state.isLoading)
            if (state.success) {
                val result = Bundle().apply {
                    putString("email", binding.etEmail.text.toString())
                    putString("password", binding.etPassword.text.toString())
                }
                setFragmentResult("requestKey", result)
                binding.root.showSuccessSnackBar(getString(R.string.register_successful))
            } else {
                state.error?.let { binding.root.showErrorSnackBar(it) }
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

                is RegisterUiEvent.NavigateToLoginScreen -> {
                    navigateToLogin()
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

    private fun loading(isLoading: Boolean) {
        binding.loading.isVisible = isLoading
        binding.loading.isClickable = isLoading

    }
}