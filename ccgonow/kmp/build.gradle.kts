//
//  Copyright 2024 zhlinh and ccgo Project Authors. All rights reserved.
//  Use of this source code is governed by a MIT-style
//  license that can be found at
//
//  https://opensource.org/license/MIT
//
//  The above copyright notice and this permission
//  notice shall be included in all copies or
//  substantial portions of the Software.

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

// ============================================================================
// Ccgonow KMP Library - Kotlin Multiplatform Wrapper
// ============================================================================
// This module provides a Kotlin Multiplatform wrapper around the native C++
// API, making it easy to use from Kotlin/Android, iOS, and Desktop applications.
//
// Features:
// - Cross-platform API with expect/actual pattern
// - JNI bridge for Android/Desktop
// - Objective-C/Swift bridge for iOS
// - Maven publishing support
// ============================================================================

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("maven-publish")
}

group = "com.mojeter.ccgo"
version = "1.0.0"

kotlin {
    // Android target
    androidTarget {
        publishLibraryVariants("release", "debug")
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    // iOS targets
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // Desktop Native targets for macOS and Linux
    macosX64()
    macosArm64()

    linuxX64()
    linuxArm64()

    // Desktop JVM target for Windows
    // Windows uses JVM because CCGO builds with MSVC, while Kotlin/Native uses MinGW (incompatible)
    jvm("desktop") {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    // CocoaPods configuration for iOS
    cocoapods {
        summary = "Ccgonow Kotlin Multiplatform Library"
        homepage = "git@github.com:zhlinh/ccgonow.git"
        ios.deploymentTarget = "14.0"

        framework {
            baseName = "CcgonowKMP"
            isStatic = true
        }

        // Link to native C++ framework
        // pod("Ccgonow") {
        //     version = "1.0.0"
        //     source = path(project.file("../ios"))
        // }
    }

    sourceSets {
        // Common source set
        val commonMain by getting {
            dependencies {
                // Add common dependencies here
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        // Android source set
        val androidMain by getting {
            dependencies {
                // Android-specific dependencies
                implementation("androidx.core:core-ktx:1.12.0")
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        // iOS source set
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        // Desktop JVM source set (for Windows)
        val desktopMain by getting {
            dependencies {
                // Desktop-specific dependencies
            }
        }

        val desktopTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        // Native desktop targets (macOS, Linux)
        val macosX64Main by getting
        val macosArm64Main by getting
        val linuxX64Main by getting
        val linuxArm64Main by getting

        // macOS source set (independent from Linux)
        val macosMain by creating {
            dependsOn(commonMain)
            macosX64Main.dependsOn(this)
            macosArm64Main.dependsOn(this)
        }

        // Linux source set (independent from macOS)
        val linuxMain by creating {
            dependsOn(commonMain)
            linuxX64Main.dependsOn(this)
            linuxArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = "com.mojeter.ccgo.ccgonow.kmp"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // KMP Android uses JNI to call native C++ code
    // Native libraries should be placed in src/androidMain/jniLibs/
    // The C++ library is built separately and should be copied to jniLibs before building KMP
}

// Maven publishing configuration
publishing {
    publications {
        // Note: Kotlin Multiplatform automatically creates publications for each target
        // We just need to configure the metadata here

        // Configure all publications
        withType<MavenPublication> {
            pom {
                name.set("Ccgonow KMP - $artifactId")
                description.set("Ccgonow Kotlin Multiplatform Library")
                url.set("git@github.com:zhlinh/ccgonow.git")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("zhlinh")
                        name.set("zhlinh")
                        email.set("zhlinhng@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git@github.com:zhlinh/ccgonow.git.git")
                    developerConnection.set("scm:git:git@github.com:zhlinh/ccgonow.git.git")
                    url.set("git@github.com:zhlinh/ccgonow.git")
                }
            }
        }
    }

    repositories {
        maven {
            name = "Local"
            url = uri(layout.buildDirectory.dir("repo"))
        }

        // Add your Maven repository here
        // maven {
        //     name = "MavenCentral"
        //     url = uri("https://your-maven-repo.com/releases")
        //     credentials {
        //         username = findProperty("maven.username") as String? ?: ""
        //         password = findProperty("maven.password") as String? ?: ""
        //     }
        // }
    }
}

// ============================================================================
// cinterop Configuration for Native Targets
// ============================================================================
// Configure cinterop to bind C/C++ code for all Native targets
// This allows direct C function calls without JNI overhead

kotlin {
    targets.withType<KotlinNativeTarget> {
        compilations.getByName("main") {
            cinterops {
                val Ccgonow by creating {
                    // Definition file path
                    defFile(project.file("src/nativeInterop/cinterop/Ccgonow.def"))

                    // Package name for generated Kotlin code
                    packageName("ccgonow")

                    // Include directories for header files
                    // Use includeDirs.allHeaders() instead of headerFilterOnly() for better compatibility
                    includeDirs.allHeaders(
                        project.file("../include/ccgonow/api/apple"),
                        project.file("../include/ccgonow/api/native")
                    )

                    // Compiler options - add include paths
                    val opts = mutableListOf(
                        "-I${project.file("../include/ccgonow/api/apple").absolutePath}",
                        "-I${project.file("../include/ccgonow/api/native").absolutePath}"
                    )

                    // Disable _Float16 and other unsupported features for Apple targets
                    if (this@withType.konanTarget.family.isAppleFamily) {
                        opts.addAll(listOf(
                            // Disable _Float16 type (not supported by cinterop)
                            "-D__FLT16_MANT_DIG__=0",
                            "-D__STDC_WANT_IEC_60559_TYPES_EXT__=0"
                        ))
                    }

                    compilerOpts(*opts.toTypedArray())

                    // Linker options (optional)
                    // Pass the path to native libraries if needed
                    // extraOpts("-libraryPath", project.file("../cmake_build/Native/Native.out/lib").absolutePath)
                }
            }
        }
    }
}
