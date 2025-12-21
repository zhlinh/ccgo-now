# Getting Started

This guide will help you get started with CCGONOWDEP.

## Prerequisites

- CMake 3.16+
- C++17 compatible compiler
- Platform-specific SDK (Android NDK, Xcode, etc.)

## Installation

### Using CCGO CLI

```bash
# Install ccgo
pip install ccgo

# Build for your platform
ccgo build android
ccgo build ios
ccgo build ohos
```

### Manual Build

```bash
mkdir build && cd build
cmake ..
make
```

## Platform Integration

### Android

Add the following to your `build.gradle`:

```groovy
dependencies {
    implementation 'com.mojeter.ccgo.samples:ccgonowdep:1.0.0'
}
```

### iOS / macOS

Using CocoaPods:

```ruby
pod 'ccgonowdep'
```

### OpenHarmony

Using OHPM:

```bash
ohpm install @ccgo/ccgonowdep
```

## Next Steps

- Check out the [API Reference](api/annotated.md) for detailed documentation
- Explore the examples in the `examples/` directory
