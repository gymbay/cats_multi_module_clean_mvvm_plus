package com.example.network.providers

import com.example.network.api.ImagesApi
import com.example.network.interceptors.ApiKeyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkProvider {

    private val provider: Retrofit

    init {
        provider = createProvider()
    }

    private fun createProvider(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(API_KEY))
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createImagesApi(): ImagesApi = provider.create(ImagesApi::class.java)

    companion object {

        private const val API_KEY = "bfe38157-77f1-417e-b4d6-3307a36e1c9e"
        private const val BASE_URL = "https://api.thecatapi.com/v1/"

    }

}