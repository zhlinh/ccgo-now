//
// ccgonowdep_jni.cpp
// api/android
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#ifdef __ANDROID__

#  include "ccgonowdep/api/android/ccgonowdep_jni.h"
#  include "ccgonowdep/api/native/ccgonowdep.h"

#  include <cstdint>
#  include <memory>

// Only define JNI_OnLoad when building as standalone library
// When used as a CCGO dependency, the main library will define JNI_OnLoad
// CCGO_AS_DEPENDENCY is automatically set by CCGO build system
#ifndef CCGO_AS_DEPENDENCY
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved) {
  (void)reserved;

  return JNI_VERSION_1_6;
}
#endif  // CCGO_AS_DEPENDENCY

JNIEXPORT void JNICALL CCGONOWDEP_JNI_API_DEF(setDebugLog)(JNIEnv *env, jclass cls, jboolean is_debug_log) {
  (void)env;
  (void)cls;
  ccgonowdep_SetDebugLog(is_debug_log);
}

#endif  // __ANDROID__
