package com.mojeter.ccgo.example.common

import androidx.compose.ui.window.Application
import com.mojeter.ccgo.example.common.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController =
    Application("Example Application") {
        App()
    }