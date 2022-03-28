
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("common")

    //id("com.vanniktech.maven.publish")
    `maven-publish`
}

/*mavenPublish {
    sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
}*/
repositories {
    google()
    mavenCentral()
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    api("androidx.compose.material:material:${Versions.compose}")

    testImplementation("junit:junit:4.13.2")
}
publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.cjrcodes"
            artifactId = "compose-numberpicker"
            version = "0.0.13"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
