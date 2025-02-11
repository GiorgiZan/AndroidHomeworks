package com.example.androidhomeworks.data.local.room.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidhomeworks.data.local.room.keys.RemoteKey
import com.example.androidhomeworks.data.local.room.keys.RemoteKeyDao

@Database(entities = [UserEntity::class, RemoteKey::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}