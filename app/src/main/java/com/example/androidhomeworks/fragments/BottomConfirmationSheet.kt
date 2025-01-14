package com.example.androidhomeworks.fragments

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.androidhomeworks.cards.CardViewModel
import com.example.androidhomeworks.databinding.FragmentBottomSheetBinding

class BottomConfirmationSheet :
    BaseBottomSheetDialogFragment<FragmentBottomSheetBinding>(FragmentBottomSheetBinding::inflate) {
    private val cardViewModel: CardViewModel by activityViewModels()
    private val args: BottomConfirmationSheetArgs by navArgs()


    override fun listeners() {
        binding.btnNo.setOnClickListener {
            dismiss()
        }
        binding.btnYes.setOnClickListener {
            cardViewModel.deleteCardById(args.cardID)
            dismiss()
        }
    }


}