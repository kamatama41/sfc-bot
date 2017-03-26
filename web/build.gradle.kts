import com.moowork.gradle.node.NodeExtension
import org.gradle.jvm.tasks.Jar
import org.springframework.boot.gradle.run.BootRunTask

buildscript {
    val nodePluginVersion = "1.1.1"
    val springBootVersion: String by extra
    repositories {
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("com.moowork.gradle:gradle-node-plugin:$nodePluginVersion")
    }
}

apply {
    plugin("org.springframework.boot")
    plugin("application")
    plugin("com.moowork.node")
}

val jar: Jar by tasks
jar.apply {
    baseName = "sfc-bot-web"
}

val bootRun: BootRunTask by tasks
bootRun.apply {
    // (ex) ./gradlew bootRun -Pprofile=foo
    val profile = project.findProperty("profile") ?: "local"
    setJvmArgs(listOf("-Dspring.profiles.active=$profile"))
}

dependencies {
    compile(project(":core"))
    compile("org.springframework.boot:spring-boot-starter-data-rest")
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile("org.springframework.boot:spring-boot-starter-security")
    compile("org.springframework.boot:spring-boot-devtools")
}

// Settings for Node.js
configure<NodeExtension> {
    version = "6.10.1"
    npmVersion = "4.4.1"
    yarnVersion = "0.21.3"
    download = true
}

val yarn_run_build: Task by tasks
yarn_run_build.apply {
    dependsOn("npmSetup")
}

val build: Task by tasks
build.apply {
    mustRunAfter("yarn_run_build")
}

// For Heroku deployment
val stage: Task by tasks
stage.apply {
    dependsOn("yarn_run_build")
}
