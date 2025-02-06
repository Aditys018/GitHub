plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.aditys.gojek"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aditys.gojek"
        minSdk = 26
        targetSdk = 34
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
        compose = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit & OkHttp for networking
    implementation (libs.retrofit.v2110)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)

    // Room for local caching
    implementation(libs.androidx.room.runtime.v260)
    kapt(libs.androidx.room.compiler.v260)
    implementation(libs.androidx.room.ktx.v260)

    // Hilt for Dependency Injection
    implementation (libs.hilt.android.v248)
    kapt (libs.hilt.compiler.v248)

    //Hilt for navigation
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Accompanist for pull-to-refresh
    implementation (libs.accompanist.swiperefresh)

    // Shimmer for loading animation
    implementation("com.valentinilk.shimmer:compose-shimmer:1.0.3")

    // Coil for image loading
    implementation(libs.coil.compose)


    // Testing libraries
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit.v115)
    androidTestImplementation (libs.androidx.espresso.core.v351)
}