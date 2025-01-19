package com.example.androidhomeworks.fragments

import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.databinding.FragmentWelcomeBinding

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {

    override fun listeners() {
        navigateToRegister()
        navigateToLogin()
    }

    private fun navigateToRegister() {
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment())
        }
    }

    private fun navigateToLogin() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment())
        }
    }


}