plugins {
    // 1. Core Android module type
    id("com.android.library")

    // 2. The *only* required Kotlin plugin for an Android module
    kotlin("android")

    // 3. Plugin for Hilt annotation processing
    kotlin("kapt")

    // 4. Hilt plugin
    id("dagger.hilt.android.plugin")

}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-compiler:2.47")
    implementation("com.google.android.gms:play-services-location:21.0.1")
}
