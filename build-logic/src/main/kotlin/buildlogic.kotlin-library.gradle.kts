plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover")
    id("com.diffplug.spotless")
}

// Spotless configuration for consistent code formatting
spotless {
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/**/*.kt")
        ktlint("1.7.1")
            .editorConfigOverride(
                mapOf(
                    "indent_size" to "4",
                    "continuation_indent_size" to "4",
                    "max_line_length" to "120",
                    "insert_final_newline" to "true",
                    "charset" to "utf-8",
                    "end_of_line" to "lf",
                    "trim_trailing_whitespace" to "true",
                    "trailing_comma_on_call_site" to "true",
                    "trailing_comma_on_declaration_site" to "true",
                )
            )
        leadingTabsToSpaces(4)
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlinGradle {
        target("**/*.gradle.kts")
        targetExclude("**/build/**/*.gradle.kts")
        ktlint("1.7.1")
        leadingTabsToSpaces(4)
        trimTrailingWhitespace()
        endWithNewline()
    }

    format("misc") {
        target("**/*.md", "**/.gitignore", "**/.gitattributes")
        targetExclude("**/build/**/*")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
