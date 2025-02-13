package com.example.androidhomeworks.mediator

import androidx.datastore.core.IOException
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.androidhomeworks.data.local.room.user.UserDatabase
import com.example.androidhomeworks.data.local.room.user.UserEntity
import com.example.androidhomeworks.data.remote.retrofit.RetrofitService
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class Mediator(
    private val database: UserDatabase,
    private val networkService: RetrofitService
) : RemoteMediator<Int, UserEntity>() {
    private val userDao = database.userDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    var nextPageNumber = lastItem.id / state.config.pageSize + 1
                    if( nextPageNumber > 2 ){
                        nextPageNumber = 2
                    }
                    nextPageNumber
                }
            }


            val response = networkService.getUsers(page = page ?: 1)

            val users = response.data.map { dto ->
                UserEntity(
                    id = dto.id,
                    email = dto.email,
                    firstName = dto.firstName,
                    lastName = dto.lastName,
                    avatar = dto.avatar
                )
            }

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDao.clearAll()
                }

                userDao.insertUsers(users)
            }

            MediatorResult.Success(
                endOfPaginationReached = users.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}