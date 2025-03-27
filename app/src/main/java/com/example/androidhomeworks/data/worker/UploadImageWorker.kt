package com.example.androidhomeworks.data.worker

import android.content.Context
import android.net.Uri
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.androidhomeworks.domain.Resource
import com.google.android.datatransport.runtime.logging.Logging.d
import com.google.firebase.storage.FirebaseStorage
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.tasks.await


@HiltWorker
class UploadImageWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        d("gwa", "fwa")
        val imageUriString = inputData.getString("image_uri") ?: return Result.failure()
        val imageUri = Uri.parse(imageUriString)

        return when (val result = uploadImage(imageUri)) {
            is Resource.Success -> {
                val output = workDataOf("uploaded_image_url" to result.data)
                Result.success(output)
            }
            is Resource.Error -> Result.failure()
            Resource.Loading -> Result.retry()
        }
    }
    private val storage = FirebaseStorage.getInstance()

    private suspend fun uploadImage(imageUri: Uri): Resource<String> {
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