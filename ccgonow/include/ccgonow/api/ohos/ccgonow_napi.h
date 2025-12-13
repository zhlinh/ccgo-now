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

//! \if ZH-CN
//! \brief 设置调试日志
//!
//! \param env NAPI环境
//! \param info 回调信息
//! \return napi_value
//! \else
//! \brief Set debug log
//!
//! \param env NAPI environment
//! \param info Callback info
//! \return napi_value
//! \endif
napi_value NapiCcgonowSetDebugLog(napi_env env, napi_callback_info info);


#  ifdef __cplusplus
}
#  endif

#endif  // __OHOS__

#endif  // CCGONOW_API_OHOS_CCGONOW_NAPI_H_
