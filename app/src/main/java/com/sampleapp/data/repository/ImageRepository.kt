package com.sampleapp.data.repository

class ImageRepository(
    private val imageRemoteDataSource: ImageRemoteDataSource
) {
    suspend fun getImages() = imageRemoteDataSource.getImages()
}