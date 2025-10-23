// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false

    // detekt plugin
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
}

subprojects {
    plugins.apply("io.gitlab.arturbosch.detekt")
    extensions.configure(io.gitlab.arturbosch.detekt.extensions.DetektExtension::class) {
        config.setFrom(files("$rootDir/config/detekt/config.yaml"))
        buildUponDefaultConfig = true
        parallel = true
    }
}