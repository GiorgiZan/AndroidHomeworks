package com.example.androidhomeworks.data.repository

import android.net.Uri
import com.example.androidhomeworks.domain.Resource
import com.example.androidhomeworks.domain.repository.UploadImageRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UploadImageRepositoryImpl @Inject constructor(
) : UploadImageRepository {

    private val storage = FirebaseStorage.getInstance()

    override suspend fun uploadImage(imageUri: Uri): Resource<String> {
        return try {
            val storageRef = storage.reference.child("images/${System.currentTimeMillis()}.jpg")

            val uploadTask = storageRef.putFile(imageUri).await()

            val downloadUrl = storageRef.downloadUrl.await().toString()

            Resource.Success(downloadUrl)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Upload failed")
        }
    }

}