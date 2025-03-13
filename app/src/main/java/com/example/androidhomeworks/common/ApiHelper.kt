package com.example.androidhomeworks.common


import android.content.Context
import com.example.androidhomeworks.domain.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ApiHelper(private val context: Context) {


    fun <T> handleHttpRequest(apiCall: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        if (!NetworkHelper.isNetworkAvailable(context)) {
            emit(Resource.Error(errorMessage = "No internet connection"))
        }

        val response = apiCall.invoke()
        try {
            if (response.isSuccessful) {
                emit(response.body()?.let {
                    Resource.Success(data = it)
                } ?: Resource.Error(errorMessage = "Something went wrong"))
            } else {
                emit(Resource.Error(errorMessage = response.errorBody()?.string() ?: "Error"))
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    emit(Resource.Error(errorMessage = throwable.message ?: "IO Exception"))
                }

                is HttpException -> {
                    emit(Resource.Error(errorMessage = throwable.message ?: "Http Exception"))
                }

                is IllegalStateException -> {
                    emit(
                        Resource.Error(
                            errorMessage = throwable.message ?: "Illegal State Exception"
                        )
                    )
                }

                else -> {
                    emit(Resource.Error(errorMessage = throwable.message ?: "Unknown Exception"))
                }
            }

        }
    }
}