//
// ccgonowdep_jni.h
// api/android
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#ifndef CCGONOWDEP_API_ANDROID_CCGONOWDEP_JNI_H_
#define CCGONOWDEP_API_ANDROID_CCGONOWDEP_JNI_H_

#ifdef __ANDROID__

#  include <jni.h>

#  include <string>
#
#  include "ccgonowdep/api/android/ccgonowdep_jni_config.h"

#  ifdef __cplusplus
extern "C" {
#  endif

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved);

JNIEXPORT void JNICALL CCGONOWDEP_JNI_API_DEF(setDebugLog)(JNIEnv *env, jclass cls, jboolean is_debug_log);


#  ifdef __cplusplus
}
#  endif

#endif  // __ANDROID__

#endif  // CCGONOWDEP_API_ANDROID_CCGONOWDEP_JNI_H_
