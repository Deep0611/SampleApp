package com.sampleapp.data.repository

import androidx.lifecycle.LiveData
import com.sampleapp.data.models.ImageModel
import retrofit2.Response

interface ImageRemoteDataSource {
    suspend fun getImages(): Response<List<ImageModel>>
}