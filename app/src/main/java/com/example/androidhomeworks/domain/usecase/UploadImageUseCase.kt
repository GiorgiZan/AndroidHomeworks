package com.example.androidhomeworks.domain.usecase

import android.net.Uri
import com.example.androidhomeworks.domain.Resource
import com.example.androidhomeworks.domain.repository.UploadImageRepository
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val repository: UploadImageRepository
) {
    suspend operator fun invoke(imageUri: Uri): Resource<String> {
        return repository.uploadImage(imageUri)
    }
}