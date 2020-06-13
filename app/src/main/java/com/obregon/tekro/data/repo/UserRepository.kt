package com.obregon.tekro.data.repo

import com.obregon.tekro.data.network.SearchUserApi
import com.obregon.tekro.data.response.UserDetail
import com.obregon.tekro.data.response.UserSummary
import javax.inject.Inject

interface UserRepository {
    suspend fun getUsers(name:String):List<UserSummary>
    suspend fun getUser(loginName:String): UserDetail
}

class UserRepositoryImpl @Inject constructor(private val searchUserApi: SearchUserApi):UserRepository{
    override suspend fun getUsers(name:String):List<UserSummary> {
        val response=searchUserApi.getUsers(name)
        return response.items
    }

    override suspend fun getUser(loginName:String): UserDetail {
       return searchUserApi.getUser(loginName)
    }

}
