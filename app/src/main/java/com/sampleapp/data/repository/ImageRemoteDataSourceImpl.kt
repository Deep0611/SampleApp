package com.sampleapp.data.repository

import com.sampleapp.data.models.ImageModel
import com.sampleapp.presentation.network.ApiInterface
import retrofit2.Response

class ImageRemoteDataSourceImpl(private val apiInterface: ApiInterface) : ImageRemoteDataSource {
    override suspend fun getImages(): Response<List<ImageModel>> {
        return apiInterface.getImages()
    }
}