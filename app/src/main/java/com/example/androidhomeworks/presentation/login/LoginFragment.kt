package com.example.androidhomeworks.presentation.login


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.databinding.FragmentLoginBinding
import com.example.androidhomeworks.data.local.datastore.MyDataStore
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.view_model_factory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val myDataStore by lazy { MyDataStore(requireContext()) }
    private var hasNavigatedToHome = false

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory { LoginViewModel(myDataStore) }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkLoggedInUser()
    }

    private fun checkLoggedInUser() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myDataStore.email.collectLatest { email ->
                    if (!email.isNullOrEmpty()) {
                        navigateToHome()
                    }
                }
            }
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

    private fun loginStateManagement() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginState.collectLatest { state ->
                    when (state) {
                        is Resource.Loading -> {
                            loading()
                        }

                        is Resource.Success -> {
                            if (!hasNavigatedToHome){
                                hasNavigatedToHome = true
                                loaded()
                                Snackbar.make(binding.root, "Login Successful", Snackbar.LENGTH_LONG).show()
                                myDataStore.email.collectLatest { email ->
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
        }
    }

    private fun loginViaService() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val rememberMe = binding.cbRememberMe.isChecked

        loginViewModel.login(requireContext(), email, password, rememberMe)
        loginStateManagement()
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