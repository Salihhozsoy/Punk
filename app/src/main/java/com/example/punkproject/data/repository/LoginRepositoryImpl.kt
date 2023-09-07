package com.example.punkproject.data.repository

import com.example.punkproject.data.locale.UserDao
import com.example.punkproject.data.locale.UserEntity
import com.example.punkproject.data.state.LoginState
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(private val userDao: UserDao) : LoginRepository {

    override suspend fun login(email: String, password: String): LoginState {
        return if (userDao.getUser(email, password) == null) LoginState.UserNotFound
        else LoginState.Success
    }
}