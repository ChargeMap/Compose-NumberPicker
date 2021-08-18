import org.gradle.api.Project
import java.io.File
import java.util.*

fun readFile(path: String): File {
    val file = File(path)
    if (!file.canRead()) throw Throwable("Could not read file : $file")
    return file
}

fun readProperties(path: String): Properties = Properties().apply { load(readFile(path).reader()) }

val Project.gradleProperties get() = readProperties("${rootProject.rootDir}/gradle.properties")
val Project.commonGradleProperties get() = readProperties("${System.getProperty("user.home")}/.gradle/gradle.properties")
val Project.localProperties get() = readProperties("${rootProject.rootDir}/local.properties")