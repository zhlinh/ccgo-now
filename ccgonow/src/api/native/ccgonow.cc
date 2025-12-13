//
// ccgonow.cc
// api/native
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#include "ccgonow/api/native/ccgonow.h"

#include "ccgonow/base/global_var.h"

void ccgonow_SetDebugLog(bool is_debug_log) {
    ccgonow::GlobalVar::GetInstance().debug_ = is_debug_log;
}

const char* ccgonow_GetVersion(void) {
    return "1.0.0";
}

