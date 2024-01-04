plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.plugin)
    kotlin("kapt")
    alias(libs.plugins.ktlint)
    alias(libs.plugins.navigation.safeargs)
}

fun gitVersion(): String {
    val os = org.apache.commons.io.output.ByteArrayOutputStream()
    project.exec {
        commandLine = "git rev-list HEAD --first-parent --count".split(" ")
        standardOutput = os
    }
    return String(os.toByteArray()).trim()
}

val versionMajor by extra { 1 }
val versionMinor by extra { 0 }
val versionRelease by extra { 0 }

val versionPatch by extra { gitVersion() }
val versionCod by extra { ("${(((versionMajor * 10000) + (versionMinor * 100) + (versionRelease * 10)) / 10)}" + versionPatch) }

android {
    namespace = "com.yoox.items"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yoox.items"
        minSdk = 26
        targetSdk = 34
        versionCode = versionCod.toInt()
        versionName = "${versionMajor}.${versionMinor}.${versionRelease}.${versionPatch}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "yooxitems"
            keyPassword = "yoox2024"
            storeFile = file("yoox.jks")
            storePassword = "yoox2024"
        }
        create("release") {
            keyAlias = "yooxitems"
            keyPassword = "yoox2024"
            storeFile = file("yoox.jks")
            storePassword = "yoox2024"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinComposeCompiler.get()
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    //Compose
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.compose.material3)
    implementation(libs.compose.material)
    implementation(libs.compose.preview)
    debugImplementation(libs.compose.preview.debug)
    implementation(libs.compose.activity)
    implementation(libs.compose.viewModel)
    implementation(libs.compose.swipeToRefresh)

    //Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    //Kotlin Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.playServices)

    //Unit Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidTest.rules)
    androidTestImplementation(libs.androidTest.runner)
    implementation(libs.nhaarman.mockito)
    implementation(libs.archcore.runtime)
    implementation(libs.archcore.testing)
    implementation(libs.coroutines.tests)
    implementation(libs.mockk.tests)
    implementation(libs.truth.exttruth)
    implementation(libs.truth.google)

    //Gson
    implementation(libs.gson)

    //LeakCanary
    debugImplementation(libs.leakCanary)

    //Hilt
    implementation(libs.hilt.implementation)
    kapt(libs.hilt.android.compiler)

    // Splash screen Api
    implementation(libs.splashScreenApi)

    //Image renders
    implementation(libs.glide.compose)
    implementation(libs.sharedPreferences)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gsonConverter)
    implementation(libs.loggingInterceptor)
    implementation(libs.flowCallAdapter)

    //Lottie Animations
    implementation(libs.lottieAnimations)
}