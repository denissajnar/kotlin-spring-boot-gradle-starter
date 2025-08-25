plugins {
    id("org.jetbrains.kotlinx.kover")
}

kover {
    reports {
        total {
            html {
                onCheck = false
            }
            xml {
                onCheck = false
            }
        }
    }
}