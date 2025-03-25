package com.example.androidhomeworks.presentation.screens.bottom_sheet

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.androidhomeworks.databinding.BottomSheetImageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File
import java.io.FileOutputStream

class ImageBottomSheetFragment(private val onImageSelected: (Uri) -> Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetImageBinding
    private var cameraUri: Uri? = null

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            cameraUri?.let { uri ->
                val compressedUri = compressImage(uri)
                onImageSelected(compressedUri)
            }
        }
        dismiss()
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val compressedUri = compressImage(it)
            onImageSelected(compressedUri)
        }
        dismiss()
    }

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            launchCamera()
        } else {
            Toast.makeText(requireContext(), "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetImageBinding.inflate(inflater, container, false)

        binding.btnTakePicture.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                launchCamera()
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }

        binding.btnSelectImage.setOnClickListener {
            galleryLauncher.launch("image/*")
        }

        return binding.root
    }

    private fun launchCamera() {
        cameraUri = createImageUri()
        cameraUri?.let { cameraLauncher.launch(it) }
    }

    private fun createImageUri(): Uri? {
        val context = requireContext()
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "camera_photo_${System.currentTimeMillis()}.jpg")
        return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    }

    private fun compressImage(imageUri: Uri): Uri {
        val context = requireContext()
        val bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(imageUri))

        val compressedFile = File(context.cacheDir, "compressed_${System.currentTimeMillis()}.jpg")
        FileOutputStream(compressedFile).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
        }

        return FileProvider.getUriForFile(context, "${context.packageName}.provider", compressedFile)
    }
}
