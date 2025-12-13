package com.mojeter.ccgo.ccgonow.kmp

/**
 * Android implementation of CcgonowKMP
 *
 * This implementation uses JNI to bridge to the native C++ library.
 * The native library is loaded automatically when this class is first accessed.
 *
 * Note: The native library (libccgonow.so) should be placed in
 * src/androidMain/jniLibs/<abi>/ before building.
 * You can build it using: ccgo build android
 */
actual class CcgonowKMP actual constructor() {
    companion object {
        private const val LIBRARY_NAME = "ccgonow"

        init {
            try {
                System.loadLibrary(LIBRARY_NAME)
            } catch (e: UnsatisfiedLinkError) {
                throw RuntimeException("Failed to load native library '$LIBRARY_NAME': ${e.message}", e)
            }
        }

        // Native JNI methods
        @JvmStatic
        private external fun nativeSetDebugLog(enable: Boolean)

        @JvmStatic
        private external fun nativeGetVersion(): String
    }

    actual fun setDebugLog(enable: Boolean) {
        nativeSetDebugLog(enable)
    }

    actual fun getVersion(): String {
        return nativeGetVersion()
    }

    actual fun getPlatformName(): String {
        return "Android (API ${android.os.Build.VERSION.SDK_INT})"
    }
}
