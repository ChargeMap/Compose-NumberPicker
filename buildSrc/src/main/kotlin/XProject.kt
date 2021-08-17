import org.gradle.api.Project

val Project.libProperties get() = readProperties("${rootProject.rootDir}/lib.properties")
val Project.gradleProperties get() = readProperties("${rootProject.rootDir}/gradle.properties")
val Project.commonGradleProperties get() = readProperties("${System.getProperty("user.home")}/.gradle/gradle.properties")
val Project.localProperties get() = readProperties("${rootProject.rootDir}/local.properties")