package com.mojeter.ccgo.samples.ccgonow.kmp

/**
 * Ccgonow Kotlin Multiplatform API
 *
 * This is the main entry point for the Ccgonow library in Kotlin Multiplatform projects.
 * It provides a cross-platform API that wraps the native C++ implementation.
 *
 * Usage example:
 * ```kotlin
 * val ccgonow = CcgonowKMP()
 * ccgonow.setDebugLog(true)
 * val version = ccgonow.getVersion()
 * ```
 */
expect class CcgonowKMP() {
    /**
     * Enable or disable debug logging
     *
     * @param enable true to enable debug logging, false to disable
     */
    fun setDebugLog(enable: Boolean)

    /**
     * Get the library version string
     *
     * @return Version string (e.g., "1.0.0")
     */
    fun getVersion(): String

    /**
     * Get the platform name
     *
     * @return Platform name (e.g., "Android", "iOS", "Desktop")
     */
    fun getPlatformName(): String
}

/**
 * Global instance factory for CcgonowKMP
 *
 * This provides a convenient way to access the library without managing instances manually.
 *
 * Usage:
 * ```kotlin
 * CcgonowKMP.getInstance().setDebugLog(true)
 * ```
 */
object CcgonowKMPFactory {
    private var instance: CcgonowKMP? = null

    /**
     * Get or create the singleton instance
     */
    fun getInstance(): CcgonowKMP {
        return instance ?: CcgonowKMP().also { instance = it }
    }

    /**
     * Reset the singleton instance (useful for testing)
     */
    fun reset() {
        instance = null
    }
}
