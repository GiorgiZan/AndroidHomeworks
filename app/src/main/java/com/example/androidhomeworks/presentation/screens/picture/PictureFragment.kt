package com.example.androidhomeworks.presentation.screens.picture

import com.example.androidhomeworks.databinding.FragmentPictureBinding
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.screens.bottom_sheet.ImageBottomSheetFragment


class PictureFragment : BaseFragment<FragmentPictureBinding>(FragmentPictureBinding::inflate) {
    override fun setUp() {
        binding.btnAddImage.setOnClickListener {
            val bottomSheet = ImageBottomSheetFragment{ imageUri ->
                binding.ivSelected.setImageURI(imageUri)
            }
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
    }

}