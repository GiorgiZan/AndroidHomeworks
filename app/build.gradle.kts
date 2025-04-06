plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.serialization)
    alias(libs.plugins.devtools.ksp)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.androidhomeworks"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.androidhomeworks"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.storage)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.glide)
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlinx.serialization.converter)
    implementation(libs.okhttp)
    implementation(libs.datastore.preferences)
    implementation(libs.paging.runtime)

    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)

    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)

    implementation (libs.androidx.activity.compose)
    implementation (libs.androidx.fragment.ktx)
    implementation (libs.androidx.ui)
    implementation (libs.androidx.material)
    implementation (libs.androidx.ui.tooling.preview)
    implementation (libs.androidx.material.icons.extended)
    implementation(libs.navigation.compose)

    implementation(libs.androidx.hilt.navigation.compose)

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.activity.compose)
    implementation(libs.androidx.paging.compose)

    //coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // JUnit for testing
    testImplementation(libs.junit)

    // MockK for mocking dependencies
    testImplementation ("io.mockk:mockk:1.13.2")

    // Coroutines Test
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // For testing coroutines with Dispatchers.Main (optional)
    testImplementation ("androidx.arch.core:core-testing:2.1.0")

    // Hilt (optional, for dependency injection in tests)
    testImplementation ("com.google.dagger:hilt-android-testing:2.44")

}

kapt {
    correctErrorTypes = true
}