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
    fun providesRetrofit(@Named("ApiUrl") apiUrl:String, client:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("ApiUrl")
    fun providesApiUrl():String{
        return "https://api.github.com"
    }

    @Provides
    fun provideHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient =
        OkHttpClient
            .Builder()
            //.addInterceptor(StethoInterceptor())
            .addInterceptor(interceptor)
            .cache(cache)
            .build()


    @Provides
    fun provideInterceptor():Interceptor{
       return  HttpLoggingInterceptor().apply {
           level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    fun provideCache(context: Context): Cache = Cache(context.cacheDir, 5 * 1024 * 1024)

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