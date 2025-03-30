package com.example.androidhomeworks.data.mapper

import com.example.androidhomeworks.data.remote.model.course.CourseDto
import com.example.androidhomeworks.domain.model.Course

fun CourseDto.toDomain() : Course{
    return Course(
        course = course
    )
}