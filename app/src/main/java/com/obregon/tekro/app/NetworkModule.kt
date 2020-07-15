package com.obregon.tekro.app

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun providesRetrofit(@Named("ApiUrl") apiUrl:String, client: OkHttpClient): Retrofit {
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

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient =
        OkHttpClient
            .Builder()
            //.addInterceptor(StethoInterceptor())
            .addInterceptor(interceptor)
            .cache(cache)
            .build()


    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return  HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache = Cache(context.cacheDir, 5 * 1024 * 1024)
}