plugins {
    idea
    alias(libs.plugins.dependency.check)
    alias(libs.plugins.ben.manes)
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

dependencyCheck {
    format = "ALL"
    suppressionFile = "owasp-suppressions.xml"
    failBuildOnCVSS = 8.0f
    analyzers {
        assemblyEnabled = false
        nuspecEnabled = false
        nugetconfEnabled = false
    }
}

tasks.dependencyUpdates {
    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
    revision = "release"
    gradleReleaseChannel = "current"

    rejectVersionIf {
        candidate.version.contains("alpha", ignoreCase = true) ||
                candidate.version.contains("beta", ignoreCase = true) ||
                candidate.version.contains("rc", ignoreCase = true) ||
                candidate.version.contains("cr", ignoreCase = true) ||
                candidate.version.contains("m", ignoreCase = true) ||
                candidate.version.contains("preview", ignoreCase = true) ||
                candidate.version.contains("eap", ignoreCase = true)
    }
}

group = "com.template"
version = "0.0.1-SNAPSHOT"
description = "Template project"
