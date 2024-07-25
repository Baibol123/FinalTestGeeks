// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.5.1") // Use the latest stable version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20") // Use the latest stable version
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.6.0") // Use the latest stable version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")

    }
}
plugins {
    id("com.android.application") version "7.1.3" apply false
    id("com.android.library") version "7.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
}
