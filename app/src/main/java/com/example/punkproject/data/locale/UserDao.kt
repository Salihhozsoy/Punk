package com.example.punkproject.data.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("select * from UserEntity where email=:email and password =:password")     //login için
    suspend fun getUser(email: String, password: String) :UserEntity?

    @Insert
    suspend fun insert(user: UserEntity)                                              //register için

    @Query("select * from UserEntity where email=:email")                             //register için
    suspend fun getUserByEmail(email: String) :UserEntity?
}