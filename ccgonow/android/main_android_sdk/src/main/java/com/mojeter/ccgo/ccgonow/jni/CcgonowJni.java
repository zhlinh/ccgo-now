//
// CcgonowJni.java
// jni
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

package com.mojeter.ccgo.ccgonow.jni;

public class CcgonowJni {
    static {
        try {
            System.loadLibrary("c++_shared");
            System.loadLibrary("libccgonow");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static native void setDebugLog(boolean isDebugLog);
}
