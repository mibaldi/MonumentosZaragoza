package com.mibaldi.monumentoszaragoza.di

import com.mibaldi.monumentoszaragoza.data.datasource.MonumentosRemoteDataSource
import com.mibaldi.monumentoszaragoza.data.server.MonumentosServerDataSource
import com.mibaldi.monumentoszaragoza.data.server.RemoteService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://www.zaragoza.es/sede/servicio/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().run {
            addInterceptor{
                val original = it.request()
                val request = original.newBuilder()
                    .header("accept","application/json")
                    .method(original.method,original.body).build()
                it.proceed(request)
            }
        }.addInterceptor(this).build()
    }
    @Provides
    @Singleton
    fun provideRemoteService(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): RemoteService {

        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: MonumentosServerDataSource): MonumentosRemoteDataSource
}