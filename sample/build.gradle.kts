plugins {
    id("com.android.application")
    id("kotlin-android")
    id("common")
}

android {
    kotlinOptions.jvmTarget = "1.8"
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(":lib"))

    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")

    testImplementation("junit:junit:4.13.1")
}