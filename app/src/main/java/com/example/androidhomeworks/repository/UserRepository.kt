package com.example.androidhomeworks.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.androidhomeworks.User
import com.example.androidhomeworks.datastore.UserSerializer
import kotlinx.coroutines.flow.Flow


private const val DATASTORE_FILE_NAME = "user.pb"

val Context.userDataStore: DataStore<User> by dataStore(
    fileName = DATASTORE_FILE_NAME,
    serializer = UserSerializer
)

class UserRepository(context: Context) {
    private val dataStore: DataStore<User> = context.userDataStore

    val userPreferencesFlow: Flow<User> = dataStore.data


    suspend fun saveUser(firstName: String, lastName: String, email: String) {
        dataStore.updateData { currentUser ->
            currentUser.toBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .build()
        }
    }

}