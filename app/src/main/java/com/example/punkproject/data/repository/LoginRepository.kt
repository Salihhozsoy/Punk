package com.example.punkproject.data.repository

import com.example.punkproject.data.locale.UserEntity

interface LoginRepository {

    suspend fun insert(user:UserEntity)

    suspend fun login(email:String,password:String) :UserEntity?
}