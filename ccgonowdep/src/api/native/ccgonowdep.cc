//
// ccgonowdep.cc
// api/native
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#include "ccgonowdep/api/native/ccgonowdep.h"

#include "ccgonowdep/base/global_var.h"

void ccgonowdep_SetDebugLog(bool is_debug_log) {
    ccgonowdep::GlobalVar::GetInstance().debug_ = is_debug_log;
}

const char* ccgonowdep_GetVersion(void) {
    return "1.0.0";
}

