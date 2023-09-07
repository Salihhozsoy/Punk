package com.example.punkproject.data.state

import com.example.punkproject.data.locale.UserEntity

sealed class LoginState{
    object Idle:LoginState()
    object Loading:LoginState()
    object UserNotFound:LoginState()
    object Success:LoginState()
    class Error(val throwable: Throwable?=null):LoginState()
}
