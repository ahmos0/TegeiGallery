plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.apollographql.apollo").version("2.5.9")
    id("co.touchlab.skie") version "0.6.4"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }
    val ktorVersion = "2.3.7"
    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation("com.apollographql.apollo:apollo-runtime-kotlin:2.5.9")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
            implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
            implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.github.ahmos0.apps.tegeigallery"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
