package com.tc.di.googlemaps

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.tc.data.repositories.MapRepositoryImpl
import com.tc.domain.repository.MapRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class RepositoryBindModule {
    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryBindModule {
        @Binds
        abstract fun bindMapRepository(impl: MapRepositoryImpl): MapRepository
    }


    @Module
    @InstallIn(SingletonComponent::class)
    object MapsProviderModule {


        @Provides
        @Singleton
        fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient {
            return LocationServices.getFusedLocationProviderClient(context)
        }


    }
}