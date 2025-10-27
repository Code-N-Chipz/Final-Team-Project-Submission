package com.tc.learn.di

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.tc.learn.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoilModule {
    @Provides
    @Singleton
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .placeholder(R.drawable.start_image) //TODO: Update image
            .error(R.drawable.start_image) //TODO: Update image
            .components {
                add(SvgDecoder.Factory()) // if you need SVG support
            }
            .build()
    }
}