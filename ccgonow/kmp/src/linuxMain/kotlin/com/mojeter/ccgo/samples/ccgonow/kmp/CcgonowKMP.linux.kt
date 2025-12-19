package com.mojeter.ccgo.samples.ccgonow.kmp

import kotlinx.cinterop.*
import ccgonow.*

/**
 * Linux implementation of CcgonowKMP
 *
 * This implementation uses Kotlin/Native cinterop to directly call C/C++ functions
 * without JNI overhead.
 */
@OptIn(ExperimentalForeignApi::class, kotlin.experimental.ExperimentalNativeApi::class)
actual class CcgonowKMP actual constructor() {
    actual fun setDebugLog(enable: Boolean) {
        ccgonow_set_debug_log(enable)
    }

    actual fun getVersion(): String {
        return ccgonow_get_version()?.toKString() ?: "Unknown"
    }

    actual fun getPlatformName(): String {
        val arch = Platform.cpuArchitecture.name
        return "Linux ($arch)"
    }
}
