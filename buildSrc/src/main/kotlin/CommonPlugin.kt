import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class CommonPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.findByType(BaseExtension::class.java)?.apply {
            compileSdkVersion(31)

            defaultConfig {
                versionCode = project.gradleProperties.getProperty("VERSION_CODE").toString().toInt()
                versionName = project.gradleProperties.getProperty("VERSION_NAME").toString()
                minSdk = 21
                targetSdk = 31
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables.useSupportLibrary = true
            }

            listOf(
                "debug",
                "test",
                "release",
                "androidTest",
                "main"
            ).forEach {
                sourceSets.getByName(it).java.srcDirs("src/$it/kotlin")
            }

            compileOptions.sourceCompatibility = JavaVersion.VERSION_1_8
            compileOptions.targetCompatibility = JavaVersion.VERSION_1_8

            testOptions.unitTests.isReturnDefaultValues = false

            composeOptions {
                kotlinCompilerExtensionVersion = Versions.compose
            }

            buildFeatures.compose = true
        }
    }
}