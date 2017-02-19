import com.moowork.gradle.node.NodeExtension
import org.gradle.jvm.tasks.Jar

buildscript {
    val kotlinVersion = "1.0.6"
    val springBootVersion = "1.5.1.RELEASE"
    val nodePluginVersion = "1.1.0"
    extra["kotlinVersion"] = kotlinVersion
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("com.moowork.gradle:gradle-node-plugin:$nodePluginVersion")
    }
}

apply {
    plugin("idea")
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("kotlin-jpa")
    plugin("org.springframework.boot")
    plugin("application")
    plugin("com.moowork.node")
}

repositories {
    jcenter()
}

val jar: Jar by tasks
jar.apply {
    baseName = "sfc-bot"
    version = "0.1.0-SNAPSHOT"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val kotlinVersion: String by extra
dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile("org.springframework.boot:spring-boot-starter-data-rest")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("org.springframework.boot:spring-boot-devtools")
    runtime("com.h2database:h2:1.4.193")
    testCompile("junit:junit:4.12")
}

// Settings for Node.js
configure<NodeExtension> {
    version = "6.9.5"
    npmVersion = "3.10.10"
    yarnVersion = "0.20.3"
    download = true
}

val yarn_run_build: Task by tasks
yarn_run_build.apply {
    dependsOn("npmSetup")
}

val build: Task by tasks
build.apply {
    mustRunAfter("clean", "yarn_run_build")
}

// For Heroku deployment
task("stage") {
    dependsOn("build", "clean", "yarn_run_build")
}
