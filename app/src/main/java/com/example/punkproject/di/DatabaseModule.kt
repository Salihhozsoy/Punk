package com.example.punkproject.di


import android.content.Context
import androidx.room.Room
import com.example.punkproject.Constant
import com.example.punkproject.data.locale.AppDatabase
import com.example.punkproject.data.locale.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Constant.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) :UserDao{
        return appDatabase.userDao()
    }
}