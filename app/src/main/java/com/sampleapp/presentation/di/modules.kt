package com.sampleapp.presentation.di

import com.sampleapp.data.repository.*
import com.sampleapp.data.usecases.GetRemoteImageUseCase
import com.sampleapp.presentation.network.ApiInterface
import com.sampleapp.presentation.utils.Constants
import com.sampleapp.presentation.viewModel.ImageViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<ApiInterface> {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }


    factory<ImageRemoteDataSource> { ImageRemoteDataSourceImpl(get()) }

    factory { ImageRepository(get()) }

    factory { GetRemoteImageUseCase(get()) }

    viewModel { ImageViewModel(get()) }
}

