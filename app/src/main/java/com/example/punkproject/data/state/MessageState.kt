package com.example.punkproject.data.state

sealed class MessageState{
    class Message(val message:String):MessageState()
}
