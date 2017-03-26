buildscript {
    val kotlinVersion: String by extra
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    }
}

allprojects {
    apply {
        plugin("idea")
    }
}

subprojects {
    repositories {
        jcenter()
    }

    apply {
        plugin("kotlin")
        plugin("kotlin-spring")
    }

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    val kotlinVersion: String by extra
    dependencies {
        compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
        testCompile("junit:junit:4.12")
        runtime("com.h2database:h2:1.4.194")
    }

    // START: For Heroku deployment
    val build: Task by tasks
    build.apply {
        mustRunAfter("clean")
    }

    task("stage") {
        dependsOn("clean", "build")
    }
    // END: For Heroku deployment
}
