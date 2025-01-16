package com.example.androidhomeworks.box

import com.example.androidhomeworks.field.Field
import kotlinx.serialization.Serializable

@Serializable
data class FieldBox(
    val fields: List<Field>
)