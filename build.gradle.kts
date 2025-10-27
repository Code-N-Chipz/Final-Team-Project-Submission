// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false

    id("com.google.devtools.ksp") version "2.2.20-2.0.3" apply false

//        id("com.google.devtools.ksp") version "2.2.0" // match Kotlin version

    id("com.google.dagger.hilt.android") version "2.57.1" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
}