package com.mojeter.ccgo.ccgonow.kmp

import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.UIDevice
import ccgonow.*

/**
 * iOS implementation of CcgonowKMP
 *
 * This implementation uses Kotlin/Native cinterop to bridge to the native C++ library
 * through C API functions.
 */
@OptIn(ExperimentalForeignApi::class)
actual class CcgonowKMP actual constructor() {
    actual fun setDebugLog(enable: Boolean) {
        ccgonow_set_debug_log(enable)
    }

    actual fun getVersion(): String {
        return ccgonow_get_version()?.toKString() ?: "Unknown"
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
 *                 val Ccgonow by creating {
 *                     defFile(project.file("src/nativeInterop/cinterop/Ccgonow.def"))
 *                     packageName("ccgonow")
 *
 *                     // Point to the framework
 *                     includeDirs.headerFilterOnly(
 *                         project.file("../include/ccgonow/api/apple")
 *                     )
 *                 }
 *             }
 *         }
 *     }
 * }
 */
