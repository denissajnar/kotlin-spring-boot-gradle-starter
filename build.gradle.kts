plugins {
    idea
}

allprojects {
    repositories {
        maven { url = uri("https://repo.spring.io/snapshot") }
        mavenCentral()
        gradlePluginPortal()
    }
}

idea {
    module.isDownloadJavadoc = true
    module.isDownloadSources = true
}

tasks {
    wrapper {
        distributionType = Wrapper.DistributionType.ALL
    }
}

group = "com.template"
version = "0.0.1-SNAPSHOT"
description = "Template project"
