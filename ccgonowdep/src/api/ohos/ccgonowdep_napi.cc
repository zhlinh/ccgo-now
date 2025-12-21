//
// ccgonowdep_napi.cc
// api/ohos
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#ifdef __OHOS__

#include "ccgonowdep/api/ohos/ccgonowdep_napi.h"

#include <cstdint>
#include <memory>
#include <string>

#include "ccgonowdep/api/native/ccgonowdep.h"

namespace {

// 获取布尔值参数
bool GetBoolParam(napi_env env, napi_callback_info info, size_t index) {
  size_t argc = index + 1;
  napi_value args[argc];
  napi_get_cb_info(env, info, &argc, args, nullptr, nullptr);

  if (argc <= index) {
    return false;
  }

  bool result = false;
  napi_get_value_bool(env, args[index], &result);
  return result;
}

// 创建undefined返回值
napi_value CreateUndefined(napi_env env) {
  napi_value result;
  napi_get_undefined(env, &result);
  return result;
}

}  // namespace

// 设置调试日志
napi_value NapiCcgonowdepSetDebugLog(napi_env env, napi_callback_info info) {
  bool is_debug_log = GetBoolParam(env, info, 0);
  ccgonowdep_SetDebugLog(is_debug_log);
  return CreateUndefined(env);
}

// 模块初始化
EXTERN_C_START
static napi_value Init(napi_env env, napi_value exports) {
  napi_property_descriptor desc[] = {
      {"setDebugLog", nullptr, NapiCcgonowdepSetDebugLog, nullptr, nullptr,
       nullptr, napi_default, nullptr},
  };

  napi_define_properties(env, exports, sizeof(desc) / sizeof(desc[0]), desc);
  return exports;
}
EXTERN_C_END

// 模块定义
static napi_module ccgonowdepModule = {
    .nm_version = 1,
    .nm_flags = 0,
    .nm_filename = nullptr,
    .nm_register_func = Init,
    .nm_modname = "Ccgonowdep",
    .nm_priv = ((void*)0),
    .reserved = {0},
};

// 模块注册
extern "C" __attribute__((constructor)) void RegisterCcgonowdepModule(void) {
  napi_module_register(&ccgonowdepModule);
}

#endif  // __OHOS__
