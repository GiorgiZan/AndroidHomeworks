package com.example.androidhomeworks.fragments

import android.content.Context
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun listeners() {
        val sharedPreferences =
            requireContext().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "No email found")
        binding.tvEmail.text = email
        binding.btnLogOut.setOnClickListener {
            sharedPreferences.edit()
                .clear()
                .apply()

            navigateToLogin()
        }

    }

    private fun navigateToLogin() {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToLoginFragment(),
            NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, true)
                .build()
        )
    }
}