package com.example.punkproject.data.state

import com.example.punkproject.data.model.ResponseItem

sealed class PhotoListState {
    object Idle : PhotoListState()
    object Loading : PhotoListState()
    object Empty : PhotoListState()
    class Result(val items: List<ResponseItem>) : PhotoListState()
    class Error(val throwable: Throwable? = null) : PhotoListState()
}
