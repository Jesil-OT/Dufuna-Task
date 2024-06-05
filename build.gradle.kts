// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.dagger.hilt.android) apply false

}

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(libs.safe.args)
        classpath(libs.hilt.android.gradle.plugin) // Check for the latest version
    }
}