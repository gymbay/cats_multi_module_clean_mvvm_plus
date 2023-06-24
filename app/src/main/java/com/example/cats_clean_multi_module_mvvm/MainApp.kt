package com.example.cats_clean_multi_module_mvvm

import android.app.Application
import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.imageLoader
import com.example.cats_clean_multi_module_mvvm.di.AppComponent
import com.example.cats_clean_multi_module_mvvm.di.DaggerAppComponent
import com.example.feature_cats_list.common.di.FeatureCatsListDependenciesProvider

class MainApp: Application(), ImageLoaderFactory {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .setContext(this)
            .setResources(resources)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        FeatureCatsListDependenciesProvider.dependencies = appComponent
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

}