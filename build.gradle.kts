plugins {
    alias(libs.plugins.android).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.parcelize).apply(false)
    alias(libs.plugins.library).apply(false)
    alias(libs.plugins.kotlinSerialization).apply(false)
    `kotlin-dsl`
    `maven-publish`
}

group = "org.fossify"
version = "1.0-SNAPSHOT"

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "commons"
            from(components["kotlin"])
        }
    }
    repositories {
        maven {
            name = "local-snapshots"
            url = uri("https://nexus.m1k.cloud/repository/maven-snapshots/")
            credentials {
                username = project.properties["mavenUser"].toString()
                password = project.properties["mavenPassword"].toString()
            }
        }
    }
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

