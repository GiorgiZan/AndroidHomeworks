package com.example.androidhomeworks.di

import android.content.Context
import com.example.androidhomeworks.data.remote.retrofit.BeeceptorService
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import com.example.androidhomeworks.domain.common.ApiHelper
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
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Named("Mocky")
    fun provideMockyRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Named("Beeceptor")
    fun provideBeeceptorRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder()
            .baseUrl("https://exchange-mock.free.beeceptor.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideApiHelper(@ApplicationContext context: Context): ApiHelper {
        return ApiHelper(context)
    }

    @Provides
    fun provideMockyRetrofitService(@Named("Mocky") retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    @Provides
    fun provideBeeceptorRetrofitService(@Named("Beeceptor") retrofit: Retrofit): BeeceptorService {
        return retrofit.create(BeeceptorService::class.java)
    }


}