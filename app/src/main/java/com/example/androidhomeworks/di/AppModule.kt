package com.example.androidhomeworks.di

import android.content.Context
import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    @Provides
    fun provideApiHelper(@ApplicationContext context: Context): ApiHelper{
        return ApiHelper(context)
    }

    @Provides
    fun provideRetrofitService(retrofit: Retrofit):RetrofitService{
        return retrofit.create(RetrofitService::class.java)
    }


}