plugins {
    id("com.google.gms.google-services") // firebase
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    // (for code generation):
    id("kotlin-kapt")
}

android {
    namespace = "com.fury.shopathing"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.fury.shopathing"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // --- LOCAL DATABASE (Room) ---
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    // If you use Kotlin Symbol Processing (KSP) usually, but we are using kapt for Hilt, so let's stick to kapt for consistency
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version") // Extensions for Coroutines

    // --- FIREBASE SETUP ---
    // The Firebase BoM (Bill of Materials) manages versions for us
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))

    // Firebase Authentication library
    implementation("com.google.firebase:firebase-auth")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // --- ARCHITECTURE & NAVIGATION ---
    // Hilt (Dependency Injection) - The glue of our app
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // --- NETWORK (Internet) ---
    // Retrofit (API calls)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Gson Converter (To turn JSON into Kotlin objects)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // --- UI HELPERS ---
    // Coil (Image Loading made for Compose)
    implementation("io.coil-kt:coil-compose:2.6.0")
    // Extended Icons (Optional, but good for shopping cart icons)
    implementation("androidx.compose.material:material-icons-extended:1.6.8")
}