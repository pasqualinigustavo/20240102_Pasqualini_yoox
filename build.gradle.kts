// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.navigation.safeargs) apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.navigationSafeArgs)
    }
}
