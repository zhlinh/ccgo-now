package com.mojeter.ccgo.samples.ccgonowdep.kmp

import kotlinx.cinterop.*
import platform.Foundation.*
import ccgonowdep.*

/**
 * macOS implementation of CcgonowdepKMP
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
        val processInfo = NSProcessInfo.processInfo
        val osVersion = processInfo.operatingSystemVersionString
        return "macOS $osVersion"
    }
}
