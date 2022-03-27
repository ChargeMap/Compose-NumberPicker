plugins {
    id("com.android.application")
    id("kotlin-android")
    id("common")
}

android {
    // JAVA 11
    compileOptions {
        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11
        targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
    }

    project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(":lib"))

    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.compose}")

    testImplementation("junit:junit:4.13.2")
}