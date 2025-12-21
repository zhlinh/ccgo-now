

# File ccgonow\_napi.h

[**File List**](files.md) **>** [**api**](dir_639c03aaf15c5be465216d96238b2517.md) **>** [**ohos**](dir_672aaee2cc6c6cd509a398529c6cb57c.md) **>** [**ccgonow\_napi.h**](ccgonow__napi_8h.md)

[Go to the documentation of this file](ccgonow__napi_8h.md)


```C++
//
// ccgonow_napi.h
// api/ohos
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#ifndef CCGONOW_API_OHOS_CCGONOW_NAPI_H_
#define CCGONOW_API_OHOS_CCGONOW_NAPI_H_

#ifdef __OHOS__

#  include <napi/native_api.h>

#  include <string>
#
#  include "ccgonow/api/ohos/ccgonow_napi_config.h"

#  ifdef __cplusplus
extern "C" {
#  endif

napi_value NapiCcgonowSetDebugLog(napi_env env, napi_callback_info info);


#  ifdef __cplusplus
}
#  endif

#endif  // __OHOS__

#endif  // CCGONOW_API_OHOS_CCGONOW_NAPI_H_
```


