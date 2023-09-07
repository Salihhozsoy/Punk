package com.example.punkproject.data.repository

import com.example.punkproject.data.locale.UserEntity

interface LoginRepository {

    suspend fun login(email:String,password:String) :UserEntity?
}