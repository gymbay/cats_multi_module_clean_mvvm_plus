package com.example.network.api

import com.example.network.models.Image
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ImagesApi {

    @GET("images/search")
    suspend fun getImages(@QueryMap params: Map<String, String> = emptyMap()): List<Image>

    @GET("images/{imageId}")
    suspend fun getImage(@Path("imageId") id: String): Image

}