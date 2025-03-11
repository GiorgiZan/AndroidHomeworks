package com.example.androidhomeworks.presentation.login


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.databinding.FragmentLoginBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.extension.lifecyclescope.lifecycleCollectLatest
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private var hasNavigatedToHome = false

    private val loginViewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEmail()
    }

    private fun observeEmail() {
        lifecycleCollectLatest(loginViewModel.email) { email ->
            if (!email.isNullOrEmpty()) {
                navigateToHome()
            }
        }
    }


    override fun listeners() {
        loadEmailAndPasswordFromRegistration()

        binding.btnLogin.setOnClickListener {
            observeUiEvents()
            loginViaService()
        }

        binding.btnRegister.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun loginStateManagement() {
        lifecycleCollectLatest(loginViewModel.loginState) { state ->
            when (state) {
                is Resource.Loading -> {
                    loading()
                }

                is Resource.Success -> {
                    if (!hasNavigatedToHome) {
                        hasNavigatedToHome = true
                        loaded()
                        Snackbar.make(
                            binding.root,
                            "Login Successful",
                            Snackbar.LENGTH_LONG
                        ).show()
                        loginViewModel.email.collectLatest { email ->
                            if (email.isNullOrEmpty()) {
                                navigateToHome()
                            }
                        }
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
        lifecycleCollectLatest(loginViewModel.uiEvent) { event ->
            when (event) {
                is LoginUiEvent.ShowEmailError -> {
                    binding.etEmail.error = getString(R.string.invalid_email_address)
                }

                is LoginUiEvent.ShowPasswordError -> {
                    binding.etPassword.error =
                        getString(R.string.password_should_not_be_empty)
                }

                is LoginUiEvent.LoginSuccess -> {
                    loginStateManagement()
                }
            }
        }
    }


    private fun loginViaService() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val rememberMe = binding.cbRememberMe.isChecked

        loginViewModel.login(email, password, rememberMe)

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