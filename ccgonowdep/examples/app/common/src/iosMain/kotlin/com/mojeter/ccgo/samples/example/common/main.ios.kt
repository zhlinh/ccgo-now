package com.mojeter.ccgo.samples.example.common

import androidx.compose.ui.window.Application
import com.mojeter.ccgo.samples.example.common.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController =
    Application("Example Application") {
        App()
    }