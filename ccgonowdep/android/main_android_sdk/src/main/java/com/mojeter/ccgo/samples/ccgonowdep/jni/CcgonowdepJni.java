//
// CcgonowdepJni.java
// jni
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

package com.mojeter.ccgo.samples.ccgonowdep.jni;

public class CcgonowdepJni {
    static {
        try {
            System.loadLibrary("c++_shared");
            System.loadLibrary("libccgonowdep");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static native void setDebugLog(boolean isDebugLog);
}
