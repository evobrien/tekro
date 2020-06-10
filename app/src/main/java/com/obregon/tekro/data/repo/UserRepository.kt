package com.obregon.tekro.data.repo

import android.util.Log
import com.obregon.tekro.data.network.SearchUserApi
import com.obregon.tekro.data.response.SearchUserResponse
import com.obregon.tekro.data.response.UserSummary
import javax.inject.Inject
import javax.inject.Singleton

interface UserRepository {
    suspend fun getUsers(name:String):List<UserSummary>
}

class UserRepositoryImpl @Inject constructor(private val searchUserApi: SearchUserApi):UserRepository{
    override suspend fun getUsers(name:String):List<UserSummary> {
        val response=searchUserApi.getUsers(name)
        Log.v("result",response.toString())
        return ArrayList<UserSummary>()
    }
}
