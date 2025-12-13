//
// global_var.cpp
// base
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#include "ccgonow/base/global_var.h"

namespace ccgonow {

GlobalVar &GlobalVar::GetInstance() {
  static GlobalVar instance;
  return instance;
}

void GlobalVar::Clear() {}

}  // namespace ccgonow
