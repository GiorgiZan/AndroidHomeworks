package com.example.androidhomeworks.presentation.screens.select_bottom

import android.R
import android.widget.ArrayAdapter
import com.example.androidhomeworks.databinding.FragmentSelectAccountBottomSheetBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseBottomSheetFragment
import com.example.androidhomeworks.presentation.model.UiAccount

class SelectAccountBottomSheetFragment(
    private val accountNumbers: List<UiAccount>,
    private val onAccountSelected: (UiAccount) -> Unit
) : BaseBottomSheetFragment<FragmentSelectAccountBottomSheetBinding>(FragmentSelectAccountBottomSheetBinding::inflate) {


    override fun setUp() {
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, accountNumbers.map { it.accountNumber })
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val selectedAccount = accountNumbers[position]
            onAccountSelected(selectedAccount)
            dismiss()
        }
    }
}
