package com.example.androidhomeworks.data.remote.retrofit

import com.example.androidhomeworks.data.remote.equipment.EquipmentDTO
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("499e0ffd-db69-4955-8d86-86ee60755b9c")
    suspend fun getEquipment(): Response<List<EquipmentDTO>>

}