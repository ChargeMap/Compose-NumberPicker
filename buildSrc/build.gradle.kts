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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    implementation("com.android.tools.build:gradle:7.0.0")
}