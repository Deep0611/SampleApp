package com.sampleapp.data.usecases

import com.sampleapp.data.repository.ImageRepository

class GetRemoteImageUseCase(private val imageRepository: ImageRepository) {
    suspend operator fun invoke() = imageRepository.getImages()
}