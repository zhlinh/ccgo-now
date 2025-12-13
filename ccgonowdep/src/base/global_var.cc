//
// global_var.cpp
// base
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#include "ccgonowdep/base/global_var.h"

namespace ccgonowdep {

GlobalVar &GlobalVar::GetInstance() {
  static GlobalVar instance;
  return instance;
}

void GlobalVar::Clear() {}

}  // namespace ccgonowdep
