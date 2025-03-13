package com.example.androidhomeworks.data.mapper

import com.example.androidhomeworks.data.remote.register.RegisterResponseDto
import com.example.androidhomeworks.domain.model.RegisterResponse

fun RegisterResponseDto.toDomain(): RegisterResponse{
    return RegisterResponse(
        id = id,
        token = token
    )
}