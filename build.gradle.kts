    // Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}
    buildscript {
        dependencies {
            classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")
            classpath("com.google.gms:google-services:4.4.2")
        }
    }
    extra.apply {
        set("compose_version", "1.5.0")
        set("hilt_version", "2.47")
        set("maps_compose_version", "2.10.0")
    }