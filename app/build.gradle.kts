plugins {
    alias(libs.plugins.android.application) // Assuming you use `libs.versions.toml`
}

android {
    namespace = "com.example.assignment1_ict602"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.assignment1_ict602"
        minSdk = 23 // Lowered for better device compatibility
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // AndroidX Libraries
    implementation(libs.appcompat) // Ensure mapped in libs.versions.toml
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Add manually if not using toml mappings
    implementation("androidx.appcompat:appcompat:1.6.1") // Toolbar/AppCompatActivity
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Testing Libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
