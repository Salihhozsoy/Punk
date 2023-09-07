package com.example.punkproject.data.repository

import com.example.punkproject.data.locale.UserDao
import com.example.punkproject.data.locale.UserEntity
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(private val userDao: UserDao):UserRepository {
    override suspend fun insert(user: UserEntity) {
        userDao.insert(user)
    }

    override suspend fun getUser(email: String, password: String): UserEntity? {
      return userDao.getUser(email,password)
    }
}