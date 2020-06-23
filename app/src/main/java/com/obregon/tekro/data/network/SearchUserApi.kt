package com.obregon.tekro.data.network

import com.obregon.tekro.data.response.SearchUserResponse
import com.obregon.tekro.data.response.UserDetail
import com.obregon.tekro.data.response.UserSummary
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchUserApi{

    @GET("/search/users")
    suspend fun getUsers(@Query("q")q:String): SearchUserResponse

    @GET("/users/{alias}")
    suspend fun getUser(@Path("alias")loginAlias:String): UserDetail

    @GET("/search/users")
    suspend fun getUserByPage(@Query("q")q:String,@Query("page")page:Int): SearchUserResponse
}