plugins {
    id("com.android.library")
    id("kotlin-android")
    id("common")
    id("com.vanniktech.maven.publish")
    //id("publish")
}

mavenPublish {
    sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
}

android {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("androidx.fragment:fragment-ktx:1.3.3")
    implementation("androidx.core:core-ktx:1.5.0-rc01")

    testImplementation("junit:junit:4.13.1")
}

repositories {
    mavenCentral()
}