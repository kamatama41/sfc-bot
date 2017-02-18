buildscript {
    val kotlinVersion = "1.0.6"
    val springBootVersion = "1.5.1.RELEASE"
    extra["kotlinVersion"] = kotlinVersion
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
}

apply {
    plugin("idea")
    plugin("kotlin")
    plugin("org.springframework.boot")
    plugin("application")
}

repositories {
    jcenter()
}

version = "0.1.0-SNAPSHOT"

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val kotlinVersion = extra["kotlinVersion"] as String
dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-devtools")
    testCompile("junit:junit:4.12")
}
