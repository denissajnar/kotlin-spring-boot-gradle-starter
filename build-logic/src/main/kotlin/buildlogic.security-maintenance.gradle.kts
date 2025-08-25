plugins {
    id("org.owasp.dependencycheck")
    id("com.github.ben-manes.versions")
}

dependencyCheck {
    format = org.owasp.dependencycheck.reporting.ReportGenerator.Format.ALL.name
    suppressionFile = rootProject.file("config/dependency-check-suppressions.xml")
        .takeIf { it.exists() }?.absolutePath
    failBuildOnCVSS = 7.0f

    nvd.apiKey = providers.environmentVariable("NVD_API_KEY").orNull

    analyzers.apply {
        assemblyEnabled = false
        nugetconfEnabled = false
        nodeEnabled = false
    }
}

tasks.dependencyUpdates {
    revision = "release"
    outputFormatter = "json"
    checkForGradleUpdate = true
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"

    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}