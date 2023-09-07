package com.example.punkproject.data.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: UserEntity)

    @Query("select * from UserEntity where email=:email and password =:password")
    suspend fun getUser(email: String, password: String) :UserEntity?
}