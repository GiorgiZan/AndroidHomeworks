package com.example.androidhomeworks.presentation.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentProfileBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch



@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val homeViewModel: ProfileViewModel by viewModels()

    override fun listeners() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.sessionEmail.collectLatest { sessionEmail ->
                    if (sessionEmail != null) {
                        binding.tvEmail.text = sessionEmail
                    } else {
                        homeViewModel.email.collectLatest { email ->
                            binding.tvEmail.text = email ?: "No email found"
                        }
                    }
                }
            }
        }

        binding.btnLogOut.setOnClickListener {
            homeViewModel.logout {
                navigateToLogin()
            }
        }

        binding.ivBackIcon.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun navigateToLogin() {
        findNavController().navigate(
            ProfileFragmentDirections.actionProfileFragmentToLoginFragment(),
            NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, inclusive = true)
                .setLaunchSingleTop(true)
                .build()
        )
    }


}