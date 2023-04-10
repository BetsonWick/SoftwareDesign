plugins {
    kotlin("jvm") version "1.6.10"
    id("com.google.protobuf") version "0.9.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.protobuf:protobuf-java:4.0.0-rc-2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:3.0.5")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.10.1"
    }
}
