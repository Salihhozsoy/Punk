package com.example.punkproject.data.repository

import com.example.punkproject.data.locale.UserEntity

interface UserRepository {

    suspend fun insert(user:UserEntity)

    suspend fun getUser(email:String,password:String) :UserEntity?
}