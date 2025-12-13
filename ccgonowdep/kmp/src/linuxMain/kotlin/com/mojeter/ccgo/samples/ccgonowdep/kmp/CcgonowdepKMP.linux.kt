package com.mojeter.ccgo.samples.ccgonowdep.kmp

import kotlinx.cinterop.*
import ccgonowdep.*

/**
 * Linux implementation of CcgonowdepKMP
 *
 * This implementation uses Kotlin/Native cinterop to directly call C/C++ functions
 * without JNI overhead.
 */
@OptIn(ExperimentalForeignApi::class)
actual class CcgonowdepKMP actual constructor() {
    actual fun setDebugLog(enable: Boolean) {
        ccgonowdep_SetDebugLog(enable)
    }

    actual fun getVersion(): String {
        return ccgonowdep_GetVersion()?.toKString() ?: "Unknown"
    }

    actual fun getPlatformName(): String {
        val arch = Platform.cpuArchitecture.name
        return "Linux ($arch)"
    }
}
