package com.example.punkproject.data.state

import com.example.punkproject.data.locale.UserEntity

sealed class LoginState{
    object Idle:LoginState()
    object Loading:LoginState()
    object UserNotFound:LoginState()
    class Success(val userEntity: UserEntity):LoginState()
    class Error(val throwable: Throwable?=null):LoginState()
}
