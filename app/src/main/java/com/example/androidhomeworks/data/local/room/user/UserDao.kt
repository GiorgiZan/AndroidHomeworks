package com.example.androidhomeworks.data.local.room.user

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserEntity>)


    @Query("SELECT * FROM users ORDER BY id ASC")
    fun getUsers(): PagingSource<Int, UserEntity>

    @Query("DELETE FROM users")
    suspend fun clearAll()
}