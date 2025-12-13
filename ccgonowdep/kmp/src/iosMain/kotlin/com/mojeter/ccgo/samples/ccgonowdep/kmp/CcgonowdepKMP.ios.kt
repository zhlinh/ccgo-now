package com.mojeter.ccgo.samples.ccgonowdep.kmp

import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.UIDevice
import ccgonowdep.*

/**
 * iOS implementation of CcgonowdepKMP
 *
 * This implementation uses Kotlin/Native cinterop to bridge to the native C++ library
 * through C API functions.
 */
@OptIn(ExperimentalForeignApi::class)
actual class CcgonowdepKMP actual constructor() {
    actual fun setDebugLog(enable: Boolean) {
        ccgonowdep_set_debug_log(enable)
    }

    actual fun getVersion(): String {
        return ccgonowdep_get_version()?.toKString() ?: "Unknown"
    }

    actual fun getPlatformName(): String {
        val device = UIDevice.currentDevice
        val systemName = device.systemName
        val systemVersion = device.systemVersion
        return "iOS $systemVersion ($systemName)"
    }
}

/**
 * Example cinterop configuration for build.gradle.kts:
 *
 * kotlin {
 *     iosX64()
 *     iosArm64()
 *     iosSimulatorArm64()
 *
 *     sourceSets {
 *         val iosMain by creating {
 *             dependencies {
 *                 // ...
 *             }
 *         }
 *     }
 *
 *     // Configure cinterop
 *     targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
 *         compilations.getByName("main") {
 *             cinterops {
 *                 val Ccgonowdep by creating {
 *                     defFile(project.file("src/nativeInterop/cinterop/Ccgonowdep.def"))
 *                     packageName("ccgonowdep")
 *
 *                     // Point to the framework
 *                     includeDirs.headerFilterOnly(
 *                         project.file("../include/ccgonowdep/api/apple")
 *                     )
 *                 }
 *             }
 *         }
 *     }
 * }
 */
