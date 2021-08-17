plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("publish") {
            id = "publish"
            implementationClass = "PublishPlugin"
        }
        register("common") {
            id = "common"
            implementationClass = "CommonPlugin"
        }
    }
}

repositories {
    google()
    jcenter()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.0-alpha10")
}