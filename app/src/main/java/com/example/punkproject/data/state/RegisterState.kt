package com.example.punkproject.data.state

import com.example.punkproject.data.locale.UserEntity

sealed class RegisterState{
    object Idle:RegisterState()
    object Loading:RegisterState()
    class Result(val user: UserEntity):RegisterState()
    class Error(var throwable: Throwable?=null):RegisterState()
}
