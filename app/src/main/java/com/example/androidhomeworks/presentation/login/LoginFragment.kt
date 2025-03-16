package com.example.androidhomeworks.presentation.login


import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentLoginBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.extension.lifecyclescope.lifecycleCollectLatest
import com.example.androidhomeworks.presentation.extension.lifecyclescope.showErrorSnackBar
import com.example.androidhomeworks.presentation.extension.lifecyclescope.showSuccessSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {


    private val loginViewModel: LoginViewModel by viewModels()

    override fun listeners() {
        loadEmailAndPasswordFromRegistration()

        binding.btnLogin.setOnClickListener {
            loginViaService()
        }

        binding.btnRegister.setOnClickListener {
            navigateToRegister()
        }
    }

    override fun observer() {
        observeLoginState()
        observeUiEvents()
    }

    private fun observeLoginState() {
        lifecycleCollectLatest(loginViewModel.loginState) { state ->
            loading(state.isLoading)
            if (state.success) {
                binding.root.showSuccessSnackBar(getString(R.string.login_successful))
            } else {
                state.error?.let { binding.root.showErrorSnackBar(it) }
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
                    binding.etPassword.error = getString(R.string.password_should_not_be_empty)
                }

                is LoginUiEvent.NavigateToHomeScreen -> {
                    navigateToHome()
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

    private fun loading(isLoading: Boolean) {
        binding.loading.isVisible = isLoading
        binding.loading.isClickable = isLoading

    }

}