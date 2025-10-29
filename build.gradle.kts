// Top-level build file where you can add configuration options common to all sub-projects/modules.


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    id("com.google.devtools.ksp") version "2.2.20-2.0.3" apply false
    id("com.google.dagger.hilt.android") version "2.57.2" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
    // detekt plugin
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
}

subprojects {
    plugins.apply("io.gitlab.arturbosch.detekt")
    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.8")
    }
    extensions.configure(io.gitlab.arturbosch.detekt.extensions.DetektExtension::class) {
        config.setFrom(files("$rootDir/config/detekt/config.yaml"))
        parallel = true
    }
}

