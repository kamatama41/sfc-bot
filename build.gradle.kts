import org.gradle.jvm.tasks.Jar

buildscript {
    val kotlinVersion = "1.0.6"
    val springBootVersion = "1.5.1.RELEASE"
    extra["kotlinVersion"] = kotlinVersion
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
}

apply {
    plugin("idea")
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("kotlin-jpa")
    plugin("org.springframework.boot")
    plugin("application")
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

// For Heroku deployment
task("stage") {
    dependsOn("build", "clean")
}
val build: DefaultTask by tasks
build.apply {
    mustRunAfter("clean")
}
