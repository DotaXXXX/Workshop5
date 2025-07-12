plugins {
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "2.3.4"
    application
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("main.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:2.3.4")
    implementation("io.ktor:ktor-server-netty:2.3.4")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
    implementation("ch.qos.logback:logback-classic:1.4.11")

    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.4")
    testImplementation(kotlin("test"))
}