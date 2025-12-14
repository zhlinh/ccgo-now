// swift-tools-version:5.3
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "Ccgonow",
    platforms: [
        .iOS("13.0"),
        .macOS("10.15")
    ],
    products: [
        .library(name: "Ccgonow", targets: ["Ccgonow"]),
    ],
    dependencies: [
        .package(url: "https://github.com/zhlinh/ccgonowdep", from: "1.0.0"),
    ],
    targets: [
        .binaryTarget(
            name: "Ccgonow",
            path: "../cmake_build/iOS/static/out/Ccgonow.xcframework"
        ),
    ]
)