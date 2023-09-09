package com.example.punkproject.data.service

import com.example.punkproject.data.model.ResponseItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {
    @GET("v2/beers")
    suspend fun getAllPhotos(): List<ResponseItem>

    @GET("v2/beers/{id}")
    suspend fun getPhotosById(@Path("id")id: Int): List<ResponseItem>
}