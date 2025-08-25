plugins {
    idea
    alias(libs.plugins.dokka)
    id("buildlogic.security-maintenance")
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

    dokkaHtmlMultiModule.configure {
        outputDirectory.set(layout.buildDirectory.dir("dokka"))
        moduleName.set(providers.gradleProperty("dokka.moduleName").get())
    }
}

group = "com.template"
version = "0.0.1-SNAPSHOT"
description = "Template project"