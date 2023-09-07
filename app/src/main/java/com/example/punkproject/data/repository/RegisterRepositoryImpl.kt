package com.example.punkproject.data.repository

import com.example.punkproject.data.locale.UserDao
import com.example.punkproject.data.locale.UserEntity
import com.example.punkproject.data.state.RegisterState
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val userDao: UserDao) :
    RegisterRepository {
    override suspend fun insert(user: UserEntity) {
        userDao.insert(user)
    }

    override suspend fun getUserByEmail(email: String): RegisterState {
        return if (userDao.getUserByEmail(email) == null) RegisterState.Result
        else RegisterState.UserAlready
    }
}