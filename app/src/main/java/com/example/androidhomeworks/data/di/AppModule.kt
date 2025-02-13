package com.example.androidhomeworks.data.di

import android.content.Context
import com.example.androidhomeworks.common.ApiHelper
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
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