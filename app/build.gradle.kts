import org.springframework.boot.buildpack.platform.build.PullPolicy

plugins {
    id("buildlogic.spring-boot-application")
    id("buildlogic.quality-assurance")
    id("buildlogic.code-formatting")
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
                    """.trimIndent().replace("\n", " ")
                )
            }
        }
    )

    pullPolicy.set(PullPolicy.IF_NOT_PRESENT)

    imageName.set("${project.group}/${project.name}:${project.version}")

    tags.set(
        listOf(
            "${project.group}/${project.name}:latest",
            "${project.group}/${project.name}:${project.version}"
        )
    )
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set("${project.group}.${project.name}")
        }
    }
}