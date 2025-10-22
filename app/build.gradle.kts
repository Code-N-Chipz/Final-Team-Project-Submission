plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.tc.iclickipay"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.tc.iclickipay"
        minSdk = 27
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(project(":uber"))
    implementation(project(":core:design"))
    implementation(project(":delivery"))
    implementation(project(":core:di"))
    implementation(project(":pcrepair"))
    implementation(project(":doctor"))
    implementation(project(":core:domain"))
    implementation(project(":handyman"))
    implementation(project(":houseclean"))
    implementation(project(":laundry"))
    implementation(project(":babysitter"))
    implementation(project(":home"))
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":learn"))
    implementation(project(":hotel"))
    implementation(project(":mechanic"))
    implementation(project(":pet"))
    implementation(project(":chat"))
    implementation(project(":ibank"))
    implementation(project(":tinder"))
    implementation(project(":eat"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}