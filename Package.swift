// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "Flashlight",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "Flashlight",
            targets: ["FlashlightPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "FlashlightPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/FlashlightPlugin"),
        .testTarget(
            name: "FlashlightPluginTests",
            dependencies: ["FlashlightPlugin"],
            path: "ios/Tests/FlashlightPluginTests")
    ]
)