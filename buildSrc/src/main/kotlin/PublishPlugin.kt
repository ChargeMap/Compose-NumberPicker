import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.create
import java.io.File
import java.net.URI
import java.util.*

fun readFile(path: String): File {
    val file = File(path)
    if (!file.canRead()) throw Throwable("Could not read file : $file")
    return file
}

fun readProperties(path: String): Properties = Properties().apply { load(readFile(path).reader()) }

class PublishPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply("maven-publish")

        project.group = project.libProperties.getProperty("lib.group")
        project.version = project.libProperties.getProperty("version.name")

        val sourcesJar = project.tasks.create<Jar>("androidSourcesJar") {
            archiveClassifier.set("sources")
        }

        project.extensions.findByType(PublishingExtension::class.java)?.apply {
            publications.create<MavenPublication>("central") {
                artifact("${project.buildDir}/outputs/aar/lib-release.aar")
                artifact(sourcesJar)

                groupId = project.libProperties.getProperty("lib.group")
                artifactId = project.libProperties.getProperty("lib.name")
                version = project.libProperties.getProperty("version.name")

                pom.withXml {
                    asNode().apply {
                        /*appendNode("description", project.libProperties.getProperty("lib.desc"))
                        appendNode("name", project.name)
                        appendNode("url", "https://github.com/${project.libProperties.getProperty("lib.group")}/${project.libProperties.getProperty("lib.name")}")
                        appendNode("licenses").appendNode("license").apply {
                            appendNode("name", "MIT")
                            appendNode("url", "https://opensource.org/licenses/mit-license.php")
                            appendNode("distribution", "repo")
                        }
                        appendNode("developers").appendNode("developer").apply {
                            appendNode("id", project.libProperties.getProperty("lib.group"))
                            appendNode("name", project.libProperties.getProperty("lib.group"))
                        }
                        appendNode("scm").apply {
                            appendNode("url", "https://github.com/${project.libProperties.getProperty("lib.group")}/${project.libProperties.getProperty("lib.name")}")
                        }*/
                        appendNode("dependencies").apply {
                            project.configurations.getByName("implementation") {
                                dependencies.forEach {
                                    appendNode("dependency").apply {
                                        appendNode("groupId", it.group)
                                        appendNode("artifactId", it.name)
                                        appendNode("version", it.version)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            repositories {
                maven {
                    name = "Github"
                    url = URI("https://maven.pkg.github.com/Chargemap/Android-Compose-NumberPicker")
                    credentials {
                        username = project.commonGradleProperties.getProperty("gpr.user")
                        password = project.commonGradleProperties.getProperty("gpr.token")
                    }
                }
            }
        }
    }
}