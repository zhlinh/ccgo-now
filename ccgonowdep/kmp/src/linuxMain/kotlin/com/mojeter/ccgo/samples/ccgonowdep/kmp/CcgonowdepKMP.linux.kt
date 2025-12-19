package com.mojeter.ccgo.samples.ccgonowdep.kmp

import kotlinx.cinterop.*
import ccgonowdep.*
import kotlin.experimental.ExperimentalNativeApi

/**
 * Linux implementation of CcgonowdepKMP
 *
 * This implementation uses Kotlin/Native cinterop to directly call C/C++ functions
 * without JNI overhead.
 */
@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
actual class CcgonowdepKMP actual constructor() {
    actual fun setDebugLog(enable: Boolean) {
        ccgonowdep_set_debug_log(enable)
    }

    actual fun getVersion(): String {
        return ccgonowdep_get_version()?.toKString() ?: "Unknown"
    }

    actual fun getPlatformName(): String {
        val arch = Platform.cpuArchitecture.name
        return "Linux ($arch)"
    }
}
