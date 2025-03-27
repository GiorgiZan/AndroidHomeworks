package com.example.androidhomeworks.presentation.screens.picture

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.androidhomeworks.data.worker.UploadImageWorker
import com.example.androidhomeworks.domain.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PictureViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {

    private val _uploadState = MutableStateFlow<Resource<String>>(Resource.Loading)
    val uploadState = _uploadState.asStateFlow()


    fun uploadImage(imageUri: Uri) {
        val inputData = workDataOf("image_uri" to imageUri.toString())

        val uploadRequest = OneTimeWorkRequestBuilder<UploadImageWorker>()
            .setInputData(inputData)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        workManager.enqueue(uploadRequest)

        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observeForever { workInfo ->
                when (workInfo?.state) {
                    WorkInfo.State.RUNNING -> _uploadState.value = Resource.Loading
                    WorkInfo.State.SUCCEEDED -> {
                        val uploadedImageUrl = workInfo.outputData.getString("uploaded_image_url")
                        _uploadState.value = Resource.Success(uploadedImageUrl ?: "")
                    }

                    WorkInfo.State.FAILED -> _uploadState.value = Resource.Error("Upload failed")
                    else -> {}
                }
            }
    }
}