package com.mojeter.ccgo.ccgonow.kmp

import kotlinx.cinterop.*
import platform.Foundation.*
import ccgonow.*

/**
 * macOS implementation of CcgonowKMP
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
        val processInfo = NSProcessInfo.processInfo
        val osVersion = processInfo.operatingSystemVersionString
        return "macOS $osVersion"
    }
}
