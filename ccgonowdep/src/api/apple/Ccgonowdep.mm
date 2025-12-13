//
// Ccgonowdep.mm
// api/apple
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#include "ccgonowdep/api/apple/Ccgonowdep.h"
#include "ccgonowdep/api/native/ccgonowdep.h"

//-----------------------------------------------------------------------------
// C API implementations for Kotlin/Native cinterop
//-----------------------------------------------------------------------------

void ccgonowdep_set_debug_log(bool enable) {
    ccgonowdep_SetDebugLog(enable);
}

const char* ccgonowdep_get_version(void) {
    return ccgonowdep_GetVersion();
}
