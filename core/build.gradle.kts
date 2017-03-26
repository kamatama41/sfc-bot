apply {
    plugin("kotlin-jpa")
}

val springBootVersion: String by extra
dependencies {
    compile("org.twitter4j:twitter4j-core:4.0.4")
    compile("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
}
