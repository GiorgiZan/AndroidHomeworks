package com.example.androidhomeworks.data.mapper

import com.example.androidhomeworks.data.remote.login.LoginResponseDto
import com.example.androidhomeworks.domain.model.LoginResponse


fun LoginResponseDto.toDomain():LoginResponse{
    return LoginResponse(
        token = token
    )
}