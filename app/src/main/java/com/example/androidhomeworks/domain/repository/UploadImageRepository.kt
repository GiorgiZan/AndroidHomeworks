package com.example.androidhomeworks.domain.repository

import android.net.Uri
import com.example.androidhomeworks.domain.Resource

interface UploadImageRepository {
    suspend fun uploadImage(imageUri: Uri): Resource<String>
}