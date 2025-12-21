

# File ccgonow\_jni\_config.h

[**File List**](files.md) **>** [**android**](dir_04f6ed7d9051d8e87a45204db12b1e43.md) **>** [**ccgonow\_jni\_config.h**](ccgonow__jni__config_8h.md)

[Go to the documentation of this file](ccgonow__jni__config_8h.md)


```C++
//
// ccgonow_jni_config.h
// api/android
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#ifndef CCGONOW_API_ANDROID_CCGONOW_JNI_CONFIG_H_
#define CCGONOW_API_ANDROID_CCGONOW_JNI_CONFIG_H_

#ifdef __ANDROID__

#  ifndef CCGONOW_JNI_API_PREFIX
#    define CCGONOW_JNI_API_PREFIX Java_com_mojeter_ccgo_ccgonow_jni_CcgonowJni_
#  endif

// the following two macros are utility macros to concatenate two macros
#  define _TOOLS_CONCAT(a, b) a##b
#  define TOOLS_CONCAT(a, b) _TOOLS_CONCAT(a, b)

#  define CCGONOW_JNI_API_DEF(f) TOOLS_CONCAT(CCGONOW_JNI_API_PREFIX, f)

#endif  // __ANDROID__

#endif  // CCGONOW_API_ANDROID_CCGONOW_JNI_CONFIG_H_
```


