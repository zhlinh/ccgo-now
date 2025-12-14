// swift-tools-version:5.3
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "ccgonowdep",
    platforms: [
        .iOS("13.0"),
        .macOS("10.15"),
        .tvOS("13.0"),
        .watchOS("6.0")
    ],
    products: [
        .library(name: "ccgonowdep", targets: ["ccgonowdep"]),
    ],
    targets: [
        .binaryTarget(
            name: "ccgonowdep",
            path: "../cmake_build/iOS/static/out/ccgonowdep.xcframework"
        ),
    ]
)