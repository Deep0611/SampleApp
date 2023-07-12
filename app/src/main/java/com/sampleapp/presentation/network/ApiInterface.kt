package com.sampleapp.presentation.network

import com.sampleapp.data.models.ImageModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/photos")
    suspend fun getImages(): Response<List<ImageModel>>
}