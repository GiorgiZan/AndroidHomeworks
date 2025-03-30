package com.example.androidhomeworks.data.remote.model.course

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    @SerialName("course") val course: Double
)
