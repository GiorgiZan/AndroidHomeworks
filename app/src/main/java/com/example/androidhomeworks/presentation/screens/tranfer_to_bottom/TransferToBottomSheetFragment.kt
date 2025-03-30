package com.example.androidhomeworks.presentation.screens.tranfer_to_bottom

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentToAccountBottomSheetBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseBottomSheetFragment
import com.example.androidhomeworks.presentation.extension.lifecyclescope.showErrorSnackBar

class TransferToBottomSheetFragment(
    private val onTransferTypeSelected: (String, String) -> Unit
) : BaseBottomSheetFragment<FragmentToAccountBottomSheetBinding>(FragmentToAccountBottomSheetBinding::inflate) {

    override fun setUp() {

        binding.etAccount.addTextChangedListener {
            handleTextChange(binding.etAccount)
        }

        binding.etPersonalNumber.addTextChangedListener {
            handleTextChange(binding.etPersonalNumber)
        }

        binding.etPhoneNumber.addTextChangedListener {
            handleTextChange(binding.etPhoneNumber)
        }


        var selectedCurrency = getString(R.string.gel)


        binding.radioGroupCurrency.setOnCheckedChangeListener { _, checkedId ->
            selectedCurrency = when (checkedId) {
                R.id.rbGEL -> getString(R.string.gel)
                R.id.rbUSD -> getString(R.string.usd)
                else -> getString(R.string.euro)
            }
        }

        binding.btnSubmit.setOnClickListener {
            val enteredValue = when {
                binding.etAccount.isEnabled -> binding.etAccount.text.toString()
                binding.etPersonalNumber.isEnabled -> binding.etPersonalNumber.text.toString()
                binding.etPhoneNumber.isEnabled -> binding.etPhoneNumber.text.toString()
                else -> ""
            }

            if (enteredValue.isEmpty()) {
                binding.root.showErrorSnackBar(getString(R.string.choose_transfer_type))
                return@setOnClickListener
            }

            if (!validateFields()){
                return@setOnClickListener
            }

            onTransferTypeSelected(selectedCurrency, enteredValue)
            dismiss()
        }
    }

    private fun validateFields():Boolean{
        if (binding.etAccount.text.toString().length != 22 && binding.etAccount.isEnabled){
            binding.root.showErrorSnackBar(getString(R.string.account_number_length_should_be_22))
            return false
        }
        if (binding.etPersonalNumber.text.toString().length != 11 && binding.etPersonalNumber.isEnabled){
            binding.root.showErrorSnackBar(getString(R.string.personal_number_length_should_be_11))
            return false
        }
        if (binding.etPhoneNumber.text.toString().length != 9 && binding.etPhoneNumber.isEnabled){
            binding.root.showErrorSnackBar(getString(R.string.phone_number_length_should_be_9))
            return false
        }

        return true
    }

    private fun handleTextChange(enabledEditText: EditText) {
        if (enabledEditText.text.isEmpty()) {
            enableAllFields()
        } else {
            disableOtherFields(enabledEditText)
        }
    }

    private fun disableOtherFields(activeEditText: EditText) {
        binding.etAccount.isEnabled = activeEditText == binding.etAccount
        binding.etPersonalNumber.isEnabled = activeEditText == binding.etPersonalNumber
        binding.etPhoneNumber.isEnabled = activeEditText == binding.etPhoneNumber
    }

    private fun enableAllFields() {
        binding.etAccount.isEnabled = true
        binding.etPersonalNumber.isEnabled = true
        binding.etPhoneNumber.isEnabled = true
    }
}
