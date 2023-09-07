package com.example.punkproject.data.repository

import com.example.punkproject.data.locale.UserEntity
import com.example.punkproject.data.state.RegisterState

interface RegisterRepository {

    suspend fun insert(user:UserEntity)

    suspend fun getUserByEmail(email:String) : RegisterState
}