package com.example.androidhomeworks.presentation.screens.picture

import android.net.Uri
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentPictureBinding
import com.example.androidhomeworks.domain.Resource
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.screens.bottom_sheet.ImageBottomSheetFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PictureFragment : BaseFragment<FragmentPictureBinding>(FragmentPictureBinding::inflate) {

    private val viewModel: PictureViewModel by viewModels()
    private var selectedImageUri: Uri? = null

    override fun setUp() {
        binding.btnAddImage.setOnClickListener {
            val bottomSheet = ImageBottomSheetFragment { imageUri ->
                selectedImageUri = imageUri
                binding.ivSelected.setImageURI(imageUri)
            }
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }

        binding.btnUploadImage.setOnClickListener {
            selectedImageUri?.let {
                observeUploadState()
                viewModel.uploadImage(it)
            } ?: showSnackBar(getString(R.string.please_select_an_image_first))
        }

    }

    private fun observeUploadState() {
        lifecycleScope.launch {
            viewModel.uploadState.collect { state ->
                when (state) {
                    is Resource.Loading ->
                        binding.loading.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.loading.visibility = View.GONE
                        showSnackBar(getString(R.string.image_uploaded_successfully, state.data))
                    }
                    is Resource.Error -> {
                        binding.loading.visibility = View.GONE
                        showSnackBar(getString(R.string.upload_failed, state.errorMessage))
                    }
                }
            }
        }
    }


    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}
