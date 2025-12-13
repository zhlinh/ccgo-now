//
// Ccgonow.mm
// api/apple
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#include "ccgonow/api/apple/Ccgonow.h"
#include "ccgonow/api/native/ccgonow.h"

//-----------------------------------------------------------------------------
// C API implementations for Kotlin/Native cinterop
//-----------------------------------------------------------------------------

void ccgonow_set_debug_log(bool enable) {
    ccgonow_SetDebugLog(enable);
}

const char* ccgonow_get_version(void) {
    return ccgonow_GetVersion();
}
