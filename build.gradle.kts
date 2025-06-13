plugins {
    java
    id("io.qameta.allure") version "2.12.0"
}

group = "io.eroshenkoam"
version = "1.0"

allure {
    report {
        version.set("2.29.1")
    }
    adapter {
        autoconfigure.set(true)
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set("2.29.1")
            }
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    ignoreFailures = true
    useJUnitPlatform {
    }

    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.config.strategy", "dynamic")
    systemProperty("junit.jupiter.extensions.autodetection.enabled", "true")

    jvmArgs = listOf(
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED"
    )
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("commons-io:commons-io:2.15.1")
    implementation("org.apache.commons:commons-io:1.3.2")
    implementation("io.qameta.allure:allure-java-commons:2.29.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.12.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.12.2")
    implementation("org.jetbrains:annotations:24.1.0")
    implementation("org.aspectj:aspectjweaver:1.9.22")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}