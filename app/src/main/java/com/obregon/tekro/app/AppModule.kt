package com.obregon.tekro.app

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.obregon.tekro.data.network.SearchUserApi
import com.obregon.tekro.data.repo.UserRepository
import com.obregon.tekro.data.repo.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule{

    @Singleton
    @Provides
    fun providesSearchUserApi(retrofit: Retrofit): SearchUserApi{
        return retrofit.create(SearchUserApi::class.java)
    }

    @Singleton
    @Provides
    fun providesUserRepository(searchUserApi: SearchUserApi): UserRepository{
        return UserRepositoryImpl(searchUserApi)
    }

}