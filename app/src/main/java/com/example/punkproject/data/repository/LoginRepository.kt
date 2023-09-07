package com.example.punkproject.data.repository

import com.example.punkproject.data.locale.UserEntity
import com.example.punkproject.data.state.LoginState

interface LoginRepository {

    suspend fun login(email: String, password: String): LoginState
}