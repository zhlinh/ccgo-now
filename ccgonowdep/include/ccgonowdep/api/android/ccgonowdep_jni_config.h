//
// ccgonowdep_jni_config.h
// api/android
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#ifndef CCGONOWDEP_API_ANDROID_CCGONOWDEP_JNI_CONFIG_H_
#define CCGONOWDEP_API_ANDROID_CCGONOWDEP_JNI_CONFIG_H_

#ifdef __ANDROID__

#  ifndef CCGONOWDEP_JNI_API_PREFIX
#    define CCGONOWDEP_JNI_API_PREFIX Java_com_mojeter_ccgo_ccgonowdep_jni_CcgonowdepJni_
#  endif

// the following two macros are utility macros to concatenate two macros
#  define _TOOLS_CONCAT(a, b) a##b
#  define TOOLS_CONCAT(a, b) _TOOLS_CONCAT(a, b)

#  define CCGONOWDEP_JNI_API_DEF(f) TOOLS_CONCAT(CCGONOWDEP_JNI_API_PREFIX, f)

#endif  // __ANDROID__

#endif  // CCGONOWDEP_API_ANDROID_CCGONOWDEP_JNI_CONFIG_H_
