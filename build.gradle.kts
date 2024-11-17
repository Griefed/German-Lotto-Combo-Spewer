import java.text.SimpleDateFormat
import java.util.*

plugins {
    kotlin("jvm") version "2.0.21"
    id("com.gradleup.shadow") version "8.3.0"
    application
}

group = "de.griefed"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.18.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "de.griefed.MainKt"
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    manifest {
        attributes(
            mapOf(
                "Built-By" to System.getProperty("user.name"),
                "Build-Timestamp" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Date()),
                "Created-By" to "Gradle ${gradle.gradleVersion}",
                "Build-Jdk" to "${System.getProperty("java.version")} (${System.getProperty("java.vendor")} ${
                    System.getProperty("java.vm.version")
                })",
                "Build-OS" to "${System.getProperty("os.name")} ${System.getProperty("os.arch")} ${
                    System.getProperty("os.version")
                }",
                "Implementation-Vendor" to "Griefed",
                "Implementation-Version" to project.version,
                "Implementation-Title" to project.name
            )
        )
    }
}