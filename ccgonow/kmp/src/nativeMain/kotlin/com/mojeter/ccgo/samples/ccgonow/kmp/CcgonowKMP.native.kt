package com.mojeter.ccgo.samples.ccgonow.kmp

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
 *                 val Ccgonow by creating {
 *                     defFile(project.file("src/nativeInterop/cinterop/Ccgonow.def"))
 *                     packageName("ccgonow")
 *                     includeDirs.headerFilterOnly(
 *                         project.file("../include/ccgonow/api/native")
 *                     )
 *                 }
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * The Ccgonow.def file should contain:
 * ```
 * headers = ccgonow.h
 * package = ccgonow
 * libraryPaths = ../cmake_build/Native/Native.out/lib
 * staticLibraries = libccgonow.a
 * ```
 */
