# ccgonow

> Cross-platform C++ library for ccgonow, supporting Android, iOS, macOS, Windows, Linux, and OpenHarmony.


- [ccgonow - Repository]( https://github.com/zhlinh/ccgonow )

- ccgonow README

  - [English]( https://github.com/zhlinh/ccgo-now/blob/master/README.md )
  - [简体中文]( https://github.com/zhlinh/ccgo-now/blob/master/README.zh-cn.md )

- ccgonow Documentation

  - [English]( https://zhlinh-ccgo-now.pages.github.com/ )
  - [简体中文]( https://zhlinh-ccgo-now.pages.github.com/zh-cn/index.html )

## 1. Releases

See [releases](https://github.com/zhlinh/ccgo-now/-/releases) for published versions and [changelog](https://github.com/zhlinh/ccgo-now/blob/master/CHANGELOG.md) for version changes.

### Version Naming Convention

- **Android SDK**: `CCGONOW_ANDROID_SDK-{version}-{release}.aar`
- **iOS Framework**: `CCGONOW_IOS_FRAMEWORK-{version}-{release}.zip`
- **macOS Framework**: `CCGONOW_MACOS_FRAMEWORK-{version}-{release}.zip`
- **Windows Library**: `CCGONOW_WINDOWS_SDK-{version}-{release}.zip`
- **Linux Library**: `CCGONOW_LINUX_SDK-{version}-{release}.zip`
- **OpenHarmony HAR**: `CCGONOW_OHOS_SDK-{version}-{release}.har`

### Version Suffixes

- **Release version**: Tagged as `v{version}` (e.g., `v1.2.3`) with suffix `release`
- **Beta version**: Suffix format `beta.{commits}` where `{commits}` is the number of commits since the last tag (e.g., `beta.23`)
- **Dirty beta**: Suffix format `beta.{commits}-dirty` for uncommitted local changes (e.g., `beta.23-dirty`)

## 2. Installation

### 2.1 Prerequisites

Install the CCGO build tool:

```bash
pip install ccgo
```

For development (editable install):

```bash
cd ccgo
pip install -e .
```

### 2.2 Platform-Specific Requirements

#### Android
- Android NDK r25c or later
- Set `ANDROID_HOME` and `ANDROID_NDK_HOME` environment variables
- Java 11 or later

#### iOS / macOS
- Xcode 14.0 or later
- Command Line Tools for Xcode

#### Windows
- Visual Studio 2015 or later
- CMake 3.10 or later

#### Linux
- GCC 7.0 or later
- CMake 3.10 or later

#### OpenHarmony (OHOS)
- OHOS SDK API 12 or later
- Set `OHOS_SDK_HOME` environment variable
- hvigor and ohpm CLI tools

## 3. Building

All build commands should be run from the `ccgonow/` directory (the inner project directory).

### 3.1 Android

Build Android AAR package with native libraries for multiple architectures:

```bash
cd ccgonow

# Build for all architectures (armeabi-v7a, arm64-v8a, x86_64)
ccgo build android

# Build for specific architectures
ccgo build android --arch arm64-v8a,x86_64

# Generate Android Studio project
ccgo build android --ide-project
```

**Output**:
- AAR file: `bin/android/CCGONOW_ANDROID_SDK-{version}-{release}.aar`
- Archive (with unstripped .so): `bin/android/ARCHIVE_CCGONOW_ANDROID_SDK-{version}-{release}.zip`
- Build metadata: `bin/android/build_info.json`

### 3.2 iOS

Build iOS XCFramework supporting both device and simulator:

```bash
cd ccgonow

# Build XCFramework (device + simulator)
ccgo build ios

# Generate Xcode project
ccgo build ios --ide-project
```

**Output**:
- XCFramework: `bin/ios/CCGONOW_IOS_FRAMEWORK-{version}-{release}.xcframework/`
- Archive: `bin/ios/ARCHIVE_CCGONOW_IOS_FRAMEWORK-{version}-{release}.zip`
- Build metadata: `bin/ios/build_info.json`

### 3.3 macOS

Build macOS Framework:

```bash
cd ccgonow

# Build universal macOS framework (x86_64 + arm64)
ccgo build macos

# Generate Xcode project
ccgo build macos --ide-project
```

**Output**:
- Framework: `bin/macos/CCGONOW_MACOS_FRAMEWORK-{version}-{release}.framework/`
- Archive: `bin/macos/ARCHIVE_CCGONOW_MACOS_FRAMEWORK-{version}-{release}.zip`
- Build metadata: `bin/macos/build_info.json`

### 3.4 Windows

Build Windows static library:

```bash
cd ccgonow

# Build Release configuration
ccgo build windows

# Build Debug configuration
ccgo build windows --config Debug

# Generate Visual Studio project
ccgo build windows --ide-project
```

**Output**:
- Library directory: `bin/windows/CCGONOW_WINDOWS_SDK-{version}-{release}.dir/`
- Archive: `bin/windows/ARCHIVE_CCGONOW_WINDOWS_SDK-{version}-{release}.zip`
- Build metadata: `bin/windows/build_info.json`

### 3.5 Linux

Build Linux static library:

```bash
cd ccgonow

# Build static library
ccgo build linux

# Generate CodeLite project
ccgo build linux --ide-project
```

**Output**:
- Library directory: `bin/linux/CCGONOW_LINUX_SDK-{version}-{release}.dir/`
- Archive: `bin/linux/ARCHIVE_CCGONOW_LINUX_SDK-{version}-{release}.zip`
- Build metadata: `bin/linux/build_info.json`

### 3.6 OpenHarmony (OHOS)

Build OpenHarmony HAR package:

```bash
cd ccgonow

# Build for all architectures (armeabi-v7a, arm64-v8a, x86_64)
ccgo build ohos

# Build for specific architectures
ccgo build ohos --arch arm64-v8a
```

**Output**:
- HAR file: `bin/ohos/CCGONOW_OHOS_SDK-{version}-{release}.har`
- Archive: `bin/ohos/ARCHIVE_CCGONOW_OHOS_SDK-{version}-{release}.zip`
- Build metadata: `bin/ohos/build_info.json`

## 4. Testing and Benchmarking

### 4.1 Unit Tests (GoogleTest)

Run unit tests using GoogleTest framework:

```bash
cd ccgonow

# Build and run all tests
ccgo test

# Build and run specific tests with filter
ccgo test --filter "UtilsTest*"

# Build only (without running)
ccgo test --build-only

# Run only (assumes already built)
ccgo test --run-only

# Generate IDE project for tests
ccgo test --ide-project

# Pass additional GoogleTest arguments
ccgo test --gtest-args="--gtest_repeat=3 --gtest_shuffle"
```

**Output**: `cmake_build/Tests/`

### 4.2 Benchmarks (Google Benchmark)

Run performance benchmarks:

```bash
cd ccgonow

# Build and run all benchmarks
ccgo bench

# Build and run specific benchmarks with filter
ccgo bench --filter "StringBench*"

# Build only
ccgo bench --build-only

# Run only
ccgo bench --run-only

# Generate IDE project for benchmarks
ccgo bench --ide-project

# Specify output format (console, json, csv)
ccgo bench --format json

# Pass additional benchmark arguments
ccgo bench --benchmark-args="--benchmark_repetitions=3"
```

**Output**: `cmake_build/Benches/`

## 5. Documentation

### 5.1 Build Documentation

Generate Doxygen documentation:

```bash
cd ccgonow

# Build documentation
ccgo doc

# Build and serve documentation locally on port 8000
ccgo doc --serve

# Serve on custom port
ccgo doc --serve --port 8080
```

**Output**: `cmake_build/Docs/{System}.out/_html/index.html`

### 5.2 Publish Documentation

Publish documentation to GitHub Pages:

```bash
# Publish to default branch (from CCGO.toml)
ccgo publish doc

# Publish to specific branch
ccgo publish doc --branch gh-pages

# Force push (overwrite existing)
ccgo publish doc --force

# Open documentation URL after publishing
ccgo publish doc --open
```

## 6. Publishing

### 6.1 Android (Maven)

Publish Android AAR to Maven repository:

```bash
cd ccgonow
ccgo publish android
```

Requires Maven credentials configured in `android/gradle.properties`.

### 6.2 OpenHarmony (OHPM)

Publish HAR to OpenHarmony Package Manager:

```bash
cd ccgonow
ccgo publish ohos
```

Requires `ohpm` CLI to be installed and authenticated.

### 6.3 iOS / macOS

iOS and macOS frameworks are typically distributed via binary archives in releases. Use CocoaPods or Swift Package Manager for dependency management.

## 7. Project Utilities

### 7.1 Create Version Tag

```bash
# Auto-generate tag from CCGO.toml version
ccgo tag

# Create specific version tag
ccgo tag v1.2.3

# Create tag with message
ccgo tag v1.2.3 --message "Release version 1.2.3"

# Create tag without pushing
ccgo tag v1.2.3 --no-push

# Delete tag
ccgo tag --delete v1.2.3
```

### 7.2 Clean Build Artifacts

```bash
cd ccgonow

# Clean all build artifacts
ccgo clean

# Clean specific platform
ccgo clean --platform android

# Clean all platforms
ccgo clean --all

# Dry run (show what would be deleted)
ccgo clean --dry-run

# Skip confirmation prompt
ccgo clean --yes
```

### 7.3 Build Information

All builds generate a `build_info.json` file in the output directory containing:

```json
{
  "build_metadata": {
    "version": "1.0",
    "generated_at": "2025-01-24T10:30:00",
    "generator": "ccgo"
  },
  "project": {
    "name": "CCGONOW",
    "version": "1.0.0"
  },
  "git": {
    "branch": "main",
    "revision": "abc123",
    "revision_full": "abc123def456...",
    "tag": "v1.0.0",
    "is_dirty": false,
    "remote_url": "/username/repo.git"
  },
  "build": {
    "time": "2025-01-24 10:30:00",
    "timestamp": 1706090400,
    "platform": "android",
    "android": {
      "ndk_version": "25.2.9519653",
      "stl": "c++_shared",
      "min_sdk_version": "21"
    }
  },
  "environment": {
    "os": "Darwin",
    "os_version": "23.0.0",
    "python_version": "3.11.0",
    "ccgo_version": "1.0.0"
  }
}
```

## 8. Contributing

### 8.1 Setup Development Environment

After cloning the project, initialize git hooks and linting tools:

```bash
cd ccgonow

# Pull lint tools submodule
git submodule update --init --recursive

# Install linting tools (run once)
lint/install.sh cpp
```

This sets up:
- **cpplint**: Google C++ style checker
- **cppcheck**: Static analysis tool
- **lizard**: Cyclomatic complexity analyzer
- **commit-msg hook**: Validates commit message format

### 8.2 Code Style

The project follows **Google C++ Style Guide**. Use these tools for formatting:

```bash
cd ccgonow

# Format code with clang-format
lint/clangformat_project_scan.sh
# Output: clangformat_lint_detail.txt

# Fix cpplint errors automatically
lint/cpplint_project_error_fix.sh
# Output: cpplint_error_fix_lint_detail.txt

# Scan code with cpplint only
lint/cpplint_project_scan.sh
# Output: cpplint_lint_detail.txt
```

### 8.3 Static Analysis

Run static analysis with cppcheck:

```bash
cd ccgonow

# Run cppcheck
lint/cppcheck_project_scan.sh
# Output: cppcheck_lint_detail.xml
```

### 8.4 Cyclomatic Complexity Check

Ensure functions have cyclomatic complexity < 20:

```bash
cd ccgonow

# Check complexity with lizard
lint/cyclomatic_complexity_project_scan.sh
# Output: cyclomatic_complexity_lint_detail.txt
```

### 8.5 Commit Message Format

Follow [Google Angular Style](https://docs.google.com/document/d/1QrDFcIiPjSLDn3EL15IJygNPiHORgU1_OOAqWjiDU5Y/edit) for commit messages:

```
<type>(<scope>): <short summary>

<body>

<footer>
```

**Type**:
- `feat`: New feature
- `fix`: Bug fix
- `refactor`: Code refactoring
- `docs`: Documentation changes
- `style`: Code formatting
- `test`: Test changes
- `chore`: Build/tooling changes
- `ci`: CI/CD changes

**Scope**: `ccgonow|base|net|utils|build|docs|test`

**Example**:
```
feat(net): add HTTP/2 support

Includes connection pooling and stream multiplexing.

Closes #12393123459
```

**Revert commits**:
```
revert: feat(net): addHTTP/2support

This reverts commit abc123def456.
Reason: Causes memory leak in production.
```

## 9. Configuration

Project configuration is stored in `ccgonow/CCGO.toml`:

```toml
[project]
name = "ccgonow"
version = "1.0.0"

[build]
verinfo_path = "include/ccgonow/base/"
generate_json_metadata = true

[android]
project_path = "android/main_android_sdk"
merge_libs = []
exclude_libs = []

[[android.export_headers]]
src = "include/ccgonow/api/*.h"
dest = "ccgonow/api"

[ohos]
project_path = "ohos/main_ohos_sdk"
merge_libs = []
exclude_libs = []

[ios]
[[ios.export_headers]]
src = "include/ccgonow/api/*.h"
dest = "ccgonow/api"

[macos]
[[macos.export_headers]]
src = "include/ccgonow/api/*.h"
dest = "ccgonow/api"

[windows]
[[windows.export_headers]]
src = "include/ccgonow/api/*.h"
dest = "ccgonow/api"

[linux]
[[linux.export_headers]]
src = "include/ccgonow/api/*.h"
dest = "ccgonow/api"

[include]
[[include.export_headers]]
src = "include/ccgonow/api/*.h"
dest = "ccgonow/api"

[publish]
pages_branch = "gh-pages"
```

## 10. Examples

The `examples/` directory contains example projects demonstrating usage across various platforms:

- **Android**: Sample Android app using the AAR
- **iOS**: Sample iOS app using the XCFramework
- **macOS**: Sample macOS app using the Framework
- **OpenHarmony**: Sample OHOS app using the HAR

## 11. License

Copyright 2025 zhlinh and ccgonow Project Authors. All rights reserved.

See [LICENSE](LICENSE) for details.
