package com.example.network.providers

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class NetworkProviderTest {

    private val provider = NetworkProvider()

    @Test
    fun testImagesApi() = runBlocking {
        val imagesApi = provider.createImagesApi()

        val images = imagesApi.getImages(mapOf(
            "limit" to "15"
        ))

        Assert.assertEquals(images.size, 15)
    }

}