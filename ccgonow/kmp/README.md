# Ccgonow Kotlin Multiplatform Library

A Kotlin Multiplatform wrapper for the Ccgonow native C++ library, enabling seamless integration across Android, iOS, and Desktop (JVM) platforms.

## 📱 Supported Platforms

- **Android** - API 24+ (Android 7.0+) - JNI
- **iOS** - iOS 14.0+, iPadOS 14.0+ - Kotlin/Native cinterop
- **macOS** - macOS 10.15+ (Intel & Apple Silicon) - Kotlin/Native cinterop
- **Linux** - x86_64, ARM64 - Kotlin/Native cinterop
- **Windows** - Windows 10+ - JVM with JNI
  - Uses JVM target because CCGO builds with MSVC (Kotlin/Native uses MinGW)

## 🚀 Quick Start

### Gradle Dependency

Add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    // For Android projects
    implementation("com.mojeter.ccgo:ccgonow-kmp-android:1.0.0")

    // For Kotlin Multiplatform projects
    commonMain {
        dependencies {
            implementation("com.mojeter.ccgo:ccgonow-kmp:1.0.0")
        }
    }
}
```

### Basic Usage

```kotlin
import com.mojeter.ccgo.ccgonow.kmp.CcgonowKMP

// Create an instance
val ccgonow = CcgonowKMP()

// Enable debug logging
ccgonow.setDebugLog(true)

// Get library version
val version = ccgonow.getVersion()
println("Ccgonow version: $version")

// Get platform information
val platform = ccgonow.getPlatformName()
println("Running on: $platform")
```

### Using the Singleton

```kotlin
import com.mojeter.ccgo.ccgonow.kmp.CcgonowKMPFactory

// Get the singleton instance
val ccgonow = CcgonowKMPFactory.getInstance()

// Use it anywhere in your app
ccgonow.setDebugLog(true)
```

## 🏗️ Architecture

### Kotlin Multiplatform Structure

```
kmp/
├── src/
│   ├── commonMain/          # Shared API definitions (expect)
│   │   └── kotlin/
│   │       └── CcgonowKMP.kt
│   │
│   ├── androidMain/         # Android implementation (JNI bridge)
│   │   └── kotlin/
│   │       └── CcgonowKMP.android.kt
│   │
│   ├── iosMain/             # iOS implementation (cinterop)
│   │   └── kotlin/
│   │       └── CcgonowKMP.ios.kt
│   │
│   └── desktopMain/         # Desktop JVM implementation (JNI bridge)
│       └── kotlin/
│           └── CcgonowKMP.desktop.kt
│
└── build.gradle.kts         # Build configuration with Maven publishing
```

### Platform-Specific Implementations

#### Android
- Uses JNI to call native C++ functions
- Depends on `main_android_sdk` module
- Native library loaded automatically

#### iOS
- Uses Kotlin/Native cinterop
- Links to native framework via CocoaPods
- Framework name: `CcgonowKMP`

#### Desktop
- Uses JNI to call native C++ functions
- Native library must be in `java.library.path` or bundled
- Graceful fallback if native library is unavailable

## 📦 Publishing to Maven

### Local Publishing

To publish to your local Maven repository:

```bash
# From project root
ccgo publish kmp
# Select option 2 (Maven Local)

# Or manually from kmp directory
cd kmp
./gradlew publishToMavenLocal
```

Kotlin Multiplatform automatically creates publications for each target platform.

The artifacts will be available at `~/.m2/repository/com/mojeter/ccgo/`:
- `ccgonow-kmp/` - KMP metadata
- `ccgonow-kmp-android/` - Android target (if built)
- `ccgonow-kmp-androidnativedebug/` - Android Debug variant
- `ccgonow-kmp-androidnativerelease/` - Android Release variant
- `ccgonow-kmp-desktop/` - Desktop JVM target (Windows)
- `ccgonow-kmp-iosx64/` - iOS Simulator (Intel)
- `ccgonow-kmp-iosarm64/` - iOS Device (ARM64)
- `ccgonow-kmp-iossimulatorarm64/` - iOS Simulator (Apple Silicon)
- `ccgonow-kmp-macosx64/` - macOS (Intel)
- `ccgonow-kmp-macosarm64/` - macOS (Apple Silicon)
- `ccgonow-kmp-linuxx64/` - Linux (x86_64)
- `ccgonow-kmp-linuxarm64/` - Linux (ARM64)

### Publishing to Remote Maven Repository

1. **Configure credentials** in `gradle.properties` or `~/.gradle/gradle.properties`:

```properties
maven.username=your-username
maven.password=your-password
```

2. **Update repository URL** in `build.gradle.kts`:

```kotlin
maven {
    name = "YourRepo"
    url = uri("https://your-maven-repo.com/releases")
    credentials {
        username = findProperty("maven.username") as String? ?: ""
        password = findProperty("maven.password") as String? ?: ""
    }
}
```

3. **Publish**:

```bash
./gradlew publish
```

## 🔧 API Reference

### CcgonowKMP

Main class providing access to the native library.

#### Methods

| Method | Description | Parameters | Returns |
|--------|-------------|------------|---------|
| `setDebugLog(enable)` | Enable/disable debug logging | `enable: Boolean` | `Unit` |
| `getVersion()` | Get library version | - | `String` |
| `getPlatformName()` | Get current platform name | - | `String` |

### CcgonowKMPFactory

Singleton factory for convenient access.

#### Methods

| Method | Description | Returns |
|--------|-------------|---------|
| `getInstance()` | Get or create singleton | `CcgonowKMP` |
| `reset()` | Reset singleton (testing) | `Unit` |

## 📱 Platform-Specific Setup

### Android

Android uses **JNI** to call native C++ functions.

**Native libraries are built automatically** when you run `ccgo build kmp`.

The build process:
1. `ccgo build kmp` automatically calls `ccgo build android --native-only`
2. Native libraries are built and placed in the correct location
3. KMP library is compiled and packaged with the native libraries

**Manual build** (if needed):

```bash
# Build Android native libraries separately
ccgo build android --native-only

# Then build KMP
ccgo build kmp
```

**Usage**:

```kotlin
import com.mojeter.ccgo.ccgonow.kmp.CcgonowKMP

val ccgonow = CcgonowKMP()
ccgonow.setDebugLog(true)  // Calls via JNI
```

**Note**:
- KMP Android module is **independent** from the `android/` directory
- It does not depend on `main_android_sdk`
- Native libraries are automatically built and included

### iOS

The library uses CocoaPods for dependency management.

**Native libraries are built automatically** when you run `ccgo build kmp` (on macOS).

1. **Run pod install**:

```bash
cd ios
pod install
```

2. **Open the `.xcworkspace` file** (not `.xcodeproj`)

3. **Import in Swift**:

```swift
import CcgonowKMP

let lib = CcgonowKMP()
lib.setDebugLog(enable: true)
```

**Note**: `ccgo build kmp` automatically builds iOS and macOS native libraries on macOS.

### Desktop

#### macOS and Linux (Kotlin/Native)

macOS and Linux use **Kotlin/Native** with direct C++ calls (no JNI overhead).

**Native libraries are built automatically** when you run `ccgo build kmp`:
- On macOS: Automatically builds macOS native libraries
- On Linux: Automatically builds Linux native libraries

**Kotlin/Native cinterop configuration**:

The build.gradle.kts automatically configures cinterop to link with native libraries.

**Usage**:

```kotlin
import com.mojeter.ccgo.ccgonow.kmp.CcgonowKMP

val ccgonow = CcgonowKMP()
ccgonow.setDebugLog(true)  // Direct C call via cinterop
```

#### Windows (JVM with JNI)

Windows uses **JVM target** because CCGO builds with MSVC (Kotlin/Native uses MinGW).

**Native libraries are built automatically** when you run `ccgo build kmp` (on Windows).

2. **Ensure native library is in path**:

The native library must be in `java.library.path` or bundled with your application.

3. **Usage**:

```kotlin
import com.mojeter.ccgo.ccgonow.kmp.CcgonowKMP

val ccgonow = CcgonowKMP()
ccgonow.setDebugLog(true)  // JNI call
```

## 🧪 Testing

### Unit Tests

Run tests for all platforms:

```bash
./gradlew allTests
```

Platform-specific tests:

```bash
./gradlew androidUnitTest      # Android JVM tests
./gradlew iosX64Test            # iOS simulator tests
./gradlew desktopTest           # Windows JVM tests
./gradlew macosX64Test          # macOS native tests
./gradlew linuxX64Test          # Linux native tests
```

## 📚 Example Projects

See the `examples/app` directory for a complete Kotlin Multiplatform application using this library.

## 🔍 Troubleshooting

### "Native library not found" on Android

- Ensure `main_android_sdk` is included as a dependency
- Check that native library is built (`ccgo build android`)
- Verify ABI compatibility

### "Unresolved reference" on iOS

- Run `pod install` in the iOS directory
- Clean and rebuild: `./gradlew clean build`
- Check CocoaPods version (1.11+)

### cinterop build failures on Native targets

- **Missing C headers**: Ensure native libraries are built first:
  ```bash
  ccgo build macos  # or linux
  ```
- **Linker errors**: Check that static/dynamic libraries exist in expected paths
- **Header not found**: Verify header files exist in `../include/ccgonow/api/apple/` or `../include/ccgonow/api/native/`

### Windows JNI library not found

If you get "UnsatisfiedLinkError" on Windows:

- **Verify native library is built**: Run `ccgo build windows`
- **Check library path**: Ensure `ccgonow.dll` is in:
  - Same directory as executable
  - `java.library.path` system property
  - Windows `PATH` environment variable
- **Verify architecture match**: Ensure DLL architecture (x86/x64) matches JVM architecture

## 📄 License

This library is part of the Ccgonow project and follows the same license.

Copyright © 2024 zhlinh

---

**Built with CCGO** - Cross-platform C++ build system for Kotlin Multiplatform

For more information about CCGO, visit: [https://github.com/your-org/ccgo](https://github.com/your-org/ccgo)
