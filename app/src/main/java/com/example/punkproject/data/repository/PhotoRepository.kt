package com.example.punkproject.data.repository

import com.example.punkproject.data.model.ResponseItem

interface PhotoRepository {
    suspend fun getAllPhotos() :List<ResponseItem>

    suspend fun getPhotosById(id:Int) :ResponseItem
}