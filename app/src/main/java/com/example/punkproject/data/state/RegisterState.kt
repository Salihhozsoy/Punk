package com.example.punkproject.data.state

import com.example.punkproject.data.locale.UserEntity

sealed class RegisterState{
    object Idle:RegisterState()
    object Loading:RegisterState()
    object UserAlready:RegisterState()
    object Result:RegisterState()
    class Error(var throwable: Throwable?=null):RegisterState()
}
