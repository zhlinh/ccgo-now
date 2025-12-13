package com.mojeter.ccgo.ccgonowdep.kmp

/**
 * Ccgonowdep Kotlin Multiplatform API
 *
 * This is the main entry point for the Ccgonowdep library in Kotlin Multiplatform projects.
 * It provides a cross-platform API that wraps the native C++ implementation.
 *
 * Usage example:
 * ```kotlin
 * val ccgonowdep = CcgonowdepKMP()
 * ccgonowdep.setDebugLog(true)
 * val version = ccgonowdep.getVersion()
 * ```
 */
expect class CcgonowdepKMP() {
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
 * Global instance factory for CcgonowdepKMP
 *
 * This provides a convenient way to access the library without managing instances manually.
 *
 * Usage:
 * ```kotlin
 * CcgonowdepKMP.getInstance().setDebugLog(true)
 * ```
 */
object CcgonowdepKMPFactory {
    private var instance: CcgonowdepKMP? = null

    /**
     * Get or create the singleton instance
     */
    fun getInstance(): CcgonowdepKMP {
        return instance ?: CcgonowdepKMP().also { instance = it }
    }

    /**
     * Reset the singleton instance (useful for testing)
     */
    fun reset() {
        instance = null
    }
}
