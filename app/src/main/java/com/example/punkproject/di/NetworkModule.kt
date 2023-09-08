package com.example.punkproject.di

import com.example.punkproject.Constant
import com.example.punkproject.data.service.PhotoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit() : Retrofit {
        val builder = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder)
            .baseUrl(Constant.BASE_API_URL).build()
    }

    @Provides
    fun provideService(retrofit: Retrofit) : PhotoService =retrofit.create(PhotoService::class.java)

}