package com.mojeter.ccgo.samples.ccgonowdep.kmp

/**
 * DEPRECATED: This file is no longer used.
 *
 * Platform-specific implementations are now in:
 * - macosMain for macOS
 * - linuxMain for Linux
 *
 * This file is kept for backward compatibility but should not be used.
 */

// This nativeMain source set is now empty.
// macOS and Linux have their own separate implementations to avoid conflicts.

/**
 * Notes on cinterop configuration:
 *
 * The build.gradle.kts file should have:
 * ```kotlin
 * kotlin {
 *     targets.withType<KotlinNativeTarget> {
 *         compilations.getByName("main") {
 *             cinterops {
 *                 val Ccgonowdep by creating {
 *                     defFile(project.file("src/nativeInterop/cinterop/Ccgonowdep.def"))
 *                     packageName("ccgonowdep")
 *                     includeDirs.headerFilterOnly(
 *                         project.file("../include/ccgonowdep/api/native")
 *                     )
 *                 }
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * The Ccgonowdep.def file should contain:
 * ```
 * headers = ccgonowdep.h
 * package = ccgonowdep
 * libraryPaths = ../cmake_build/Native/Native.out/lib
 * staticLibraries = libccgonowdep.a
 * ```
 */
