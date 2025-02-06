package com.example.androidhomeworks.presentation.passcode


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidhomeworks.presentation.base.BaseFragment
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentPasscodeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class PasscodeFragment : BaseFragment<FragmentPasscodeBinding>(FragmentPasscodeBinding::inflate) {
    private val viewModel: PasscodeViewModel by viewModels()
    override fun setUp() {
        val circles = listOf(
            binding.circle1, binding.circle2, binding.circle3, binding.circle4
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.enteredPasscode.collect { passcode ->
                circles.forEachIndexed { index, circle ->
                    circle.setImageResource(
                        if (index < passcode.length) R.drawable.green_circle else R.drawable.circle
                    )
                }

                if (passcode.length == 4) {
                    if (viewModel.checkPasscode()) {
                        Snackbar.make(
                            binding.root,
                            getString(R.string.success), Snackbar.LENGTH_SHORT
                        ).show()
                        viewModel.resetPasscode()
                    } else {
                        Snackbar.make(
                            binding.root,
                            getString(R.string.incorrect_passcode), Snackbar.LENGTH_SHORT
                        )
                            .show()
                        viewModel.resetPasscode()
                    }
                }
            }
        }
    }

    override fun listeners() {
        listenersOfNumbersAndDelete()
    }


    private fun listenersOfNumbersAndDelete() {
        val buttons = mapOf(
            binding.btnOne to "1",
            binding.btnTwo to "2",
            binding.btnThree to "3",
            binding.btnFour to "4",
            binding.btnFive to "5",
            binding.btnSix to "6",
            binding.btnSeven to "7",
            binding.btnEight to "8",
            binding.btnNine to "9",
            binding.btnZero to "0"
        )

        buttons.forEach { (button, num) ->
            button.setOnClickListener { viewModel.addNum(num) }
        }
        binding.btnDelete.setOnClickListener { viewModel.removeLastNum() }
        binding.btnDelete.setOnLongClickListener {
            viewModel.resetPasscode()
            true
        }
    }
}