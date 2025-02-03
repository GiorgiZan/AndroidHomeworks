package com.example.androidhomeworks.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val avatar: String?,
    val first_name: String,
    val last_name: String,
    val about: String?,
    val activation_status: Double
) {
}