package com.example.punkproject.data.repository

import com.example.punkproject.data.model.ResponseItem
import com.example.punkproject.data.service.PhotoService
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val photoService: PhotoService) :PhotoRepository{
    override suspend fun getAllPhotos(): List<ResponseItem> {
        return photoService.getAllPhotos()
    }

    override suspend fun getPhotosById(id:Int): ResponseItem {
        return photoService.getPhotosById(id)
    }
}