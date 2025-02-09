package com.example.androidhomeworks.common

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

object ApiHelper {
    suspend fun <T> handleHttpRequest(apiCall: suspend () -> Response<T>): Resource<T> {
        val response = apiCall.invoke()
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(data = it)
                } ?: Resource.Error(errorMessage = "Something went wrong")
            } else {
                Resource.Error(errorMessage = response.errorBody()?.string()?: "Error")
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    Resource.Error(errorMessage = throwable.message ?: "IO Exception")
                }

                is HttpException -> {
                    Resource.Error(errorMessage = throwable.message ?: "Http Exception")
                }

                is IllegalStateException -> {
                    Resource.Error(errorMessage = throwable.message ?: "Illegal State Exception")
                }

                else -> {
                    Resource.Error(errorMessage = throwable.message ?: "Unknown Exception")
                }
            }

        }
    }
}