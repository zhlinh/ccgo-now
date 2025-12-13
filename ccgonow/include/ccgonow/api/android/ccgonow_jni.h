//
// ccgonow_jni.h
// api/android
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#ifndef CCGONOW_API_ANDROID_CCGONOW_JNI_H_
#define CCGONOW_API_ANDROID_CCGONOW_JNI_H_

#ifdef __ANDROID__

#  include <jni.h>

#  include <string>
#
#  include "ccgonow/api/android/ccgonow_jni_config.h"

#  ifdef __cplusplus
extern "C" {
#  endif

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved);

JNIEXPORT void JNICALL CCGONOW_JNI_API_DEF(setDebugLog)(JNIEnv *env, jclass cls, jboolean is_debug_log);


#  ifdef __cplusplus
}
#  endif

#endif  // __ANDROID__

#endif  // CCGONOW_API_ANDROID_CCGONOW_JNI_H_
