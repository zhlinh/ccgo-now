# Apple IDE Integration

This directory provides Xcode integration for the CCGONOW project.

## Quick Start

### Opening in Xcode

1. Open `ccgonow.xcworkspace` in Xcode
2. Select a scheme from the scheme selector:
   - `CCGONOW-macOS` - Build for macOS
   - `CCGONOW-iOS` - Build for iOS
   - `CCGONOW-tvOS` - Build for tvOS
   - `CCGONOW-watchOS` - Build for watchOS
   - `CCGONOW-Tests` - Run unit tests
3. Press `Cmd+B` to build

### Prerequisites

- Xcode 14.0 or later
- ccgo CLI installed (`pip install ccgo`)
- CMake 3.15 or later

## Directory Structure

```
apple/
├── ccgonow.xcworkspace/  # Xcode workspace (open this)
├── ccgonow.xcodeproj/    # Xcode project configuration
├── apple_native_stub/    # CMake bridge for IDE features
│   └── CMakeLists.txt
└── README.md             # This file
```

## How It Works

This Xcode project uses **Legacy Build System** targets that invoke `ccgo` commands:

| Scheme | Command |
|--------|---------|
| CCGONOW-macOS | `ccgo build macos` |
| CCGONOW-iOS | `ccgo build ios` |
| CCGONOW-tvOS | `ccgo build tvos` |
| CCGONOW-watchOS | `ccgo build watchos` |
| CCGONOW-Tests | `ccgo test` |

The workspace also references the `src/` and `include/` directories for:
- Code navigation and browsing
- IntelliSense / code completion
- Symbol search

## Code Navigation

Xcode will index source files from:
- `../src/` - Source files
- `../include/` - Header files
- `../tests/` - Test files

You can:
- Jump to definition (`Cmd+Click`)
- Find references (`Ctrl+Cmd+J`)
- Use the symbol navigator (`Cmd+Shift+O`)

## Building

### From Xcode

1. Select the target scheme (e.g., `CCGONOW-iOS`)
2. Press `Cmd+B` or select Product → Build

### From Command Line

For production builds, use ccgo directly:

```bash
cd ..
ccgo build ios      # Build iOS XCFramework
ccgo build macos    # Build macOS framework
ccgo build tvos     # Build tvOS XCFramework
ccgo build watchos  # Build watchOS XCFramework
```

## Debugging

To debug C++ code:

1. Set breakpoints in source files
2. Select a test scheme (e.g., `CCGONOW-Tests`)
3. Press `Cmd+R` to run with debugger

Note: Debugging requires the test binary to be built first.

## See Also

- [Root README](../README.md)
- [Android Integration](../android/README.md)
