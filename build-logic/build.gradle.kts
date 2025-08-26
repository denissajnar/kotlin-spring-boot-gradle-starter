plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(plugin(libs.plugins.spring.boot))
    implementation(plugin(libs.plugins.spring.dependency.management))
    implementation(plugin(libs.plugins.graalvm.native))
    implementation(plugin(libs.plugins.kotlin.spring))
    implementation(plugin(libs.plugins.kotlin.jvm))
    implementation(plugin(libs.plugins.dokka))
    implementation(plugin(libs.plugins.kover))
    implementation(plugin(libs.plugins.spotless))
}

fun DependencyHandlerScope.plugin(plugin: Provider<PluginDependency>) =
    plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }