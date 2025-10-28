package com.tc.mechanic

import com.tc.auth.ui.navigation.AppNavigator
import com.tc.auth.ui.navigation.ComposeAppNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindAppNavigator(
        composeAppNavigator: ComposeAppNavigator
    ): AppNavigator
}