plugins {
    id("buildlogic.kotlin-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.plugin.spring")
    id("org.graalvm.buildtools.native")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("io.mockk:mockk:1.14.5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.bootJar {
    archiveClassifier.set("")
}

tasks.jar {
    enabled = false
}

tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-buildpackless-tiny:latest")
    if (project.hasProperty("native")) {
        buildpacks.set(
            listOf(
                "paketobuildpacks/java-native-image"
            )
        )
    }

    environment.set(
        mutableMapOf<String, String>(
            "BP_JVM_VERSION" to "24",
            "BP_JVM_TYPE" to "JRE"
        ).apply {
            if (project.hasProperty("native")) {
                put("BP_NATIVE_IMAGE", "true")
                put(
                    "BP_NATIVE_IMAGE_BUILD_ARGUMENTS",
                    """
                    --no-fallback
                    --enable-url-protocols=http,https
                    -H:+ReportExceptionStackTraces
                    --initialize-at-build-time=org.slf4j
                    """.trimIndent().replace("\n", " ")
                )
            }
        }
    )

    imageName.set("${project.group}/${project.name}:${project.version}")

    tags.set(
        listOf(
            "${project.group}/${project.name}:latest",
            "${project.group}/${project.name}:${project.version}"
        )
    )
}