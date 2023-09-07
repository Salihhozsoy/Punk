package com.example.punkproject.data.repository

import com.example.punkproject.data.locale.UserDao
import com.example.punkproject.data.locale.UserEntity
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(private val userDao: UserDao):LoginRepository {
    override suspend fun insert(user: UserEntity) {
        userDao.insert(user)
    }

    override suspend fun login(email: String, password: String): UserEntity? {
      return userDao.getUser(email,password)
    }
}