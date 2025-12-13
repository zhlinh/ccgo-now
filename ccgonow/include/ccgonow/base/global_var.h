//
// global_var.h
// base
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#ifndef CCGONOW_BASE_GLOBAL_VAR_H_
#define CCGONOW_BASE_GLOBAL_VAR_H_

#include <map>
#include <set>
#include <sstream>
#include <string>
#include <unordered_map>
#include <unordered_set>
#include <vector>

#ifdef __ANDROID__
#  include <jni.h>
#endif

namespace ccgonow {

#define CCGONOW_GV (ccgonow::GlobalVar::GetInstance())
#define CCGONOW_DEBUG (ccgonow::GlobalVar::GetInstance().debug_)

class GlobalVar {
 private:
  GlobalVar() = default;
  ~GlobalVar() = default;
  GlobalVar(const GlobalVar &) = delete;
  GlobalVar &operator=(const GlobalVar &) = delete;

 public:
  static GlobalVar &GetInstance();

  void Clear();

  bool debug_ = false;
};

}  // namespace ccgonow

#endif  // CCGONOW_BASE_GLOBAL_VAR_H_
