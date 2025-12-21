

# File ccgonow.h

[**File List**](files.md) **>** [**api**](dir_639c03aaf15c5be465216d96238b2517.md) **>** [**native**](dir_75eae66b30959e73daa92e063f248b66.md) **>** [**ccgonow.h**](native_2_ccgonow_8h.md)

[Go to the documentation of this file](native_2_ccgonow_8h.md)


```C++
//
// ccgonow.h
// api/native
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#ifndef CCGONOW_API_NATIVE_CCGONOW_H_
#define CCGONOW_API_NATIVE_CCGONOW_H_

#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

#ifndef COMM_PUBLIC
// this is for non-project use, no need to define COMM_PUBLIC
// or windows will add __imp_ prefix when searching for symbols
#  if defined(COMM_ENABLE_EXPORTS) && COMM_ENABLE_EXPORTS == 1
#    if defined _WIN32 || defined __CYGWIN__
#      ifdef BUILDING_DLL
#        ifdef __GNUC__
#          define COMM_PUBLIC __attribute__((dllexport))
#        else
#          define COMM_PUBLIC __declspec(dllexport)
#        endif
#      else
#        ifdef __GNUC__
#          define COMM_PUBLIC __attribute__((dllimport))
#        else
#          define COMM_PUBLIC __declspec(dllimport)
#        endif
#      endif
#    else
#      if defined(__clang__) || (defined(__GNUC__) && __GNUC__ >= 4)
#        define COMM_PUBLIC __attribute__((visibility("default")))
#      else
#        define COMM_PUBLIC
#      endif
#    endif
#  else
#    define COMM_PUBLIC
#  endif  // defined(COMM_ENABLE_EXPORTS) && COMM_ENABLE_EXPORTS == 1
#endif    // #ifndef (COMM_PUBLIC)

void ccgonow_SetDebugLog(bool is_debug_log);

const char* ccgonow_GetVersion(void);


#ifdef __cplusplus
}
#endif

#endif  // CCGONOW_API_NATIVE_FOUNDRY_COMM_LOG_H_
```


