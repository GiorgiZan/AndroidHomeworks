package com.example.androidhomeworks.presentation.profile

import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentProfileBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.extension.lifecyclescope.lifecycleCollectLatest
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val homeViewModel: ProfileViewModel by viewModels()

    override fun listeners() {
        lifecycleCollectLatest(homeViewModel.email) { email ->
            binding.tvEmail.text = email
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

    override fun observer() {

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