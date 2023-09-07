package com.example.punkproject.di

import com.example.punkproject.data.repository.LoginRepository
import com.example.punkproject.data.repository.LoginRepositoryImpl
import com.example.punkproject.data.repository.RegisterRepository
import com.example.punkproject.data.repository.RegisterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository =
        loginRepositoryImpl

    @Provides
    @Singleton
    fun provideUserRepository(userRepositoryImpl: RegisterRepositoryImpl): RegisterRepository =
        userRepositoryImpl

}