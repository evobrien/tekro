package com.obregon.tekro.app

import com.obregon.tekro.data.network.SearchUserApi
import com.obregon.tekro.data.repo.UserRepository
import com.obregon.tekro.data.repo.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule{

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