package com.sampleapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sampleapp.data.models.ImageModel
import com.sampleapp.data.usecases.GetRemoteImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel(
    private val getRemoteImageUseCase: GetRemoteImageUseCase,
) : ViewModel() {
    var mutableImageList = MutableLiveData<List<ImageModel>>()
    var imageList: LiveData<List<ImageModel>> = mutableImageList

    fun getRemoteImages() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getRemoteImageUseCase.invoke()
                if (response.isSuccessful) {
                    response.body()?.let { jokeModel ->
                        mutableImageList.postValue(jokeModel);
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}