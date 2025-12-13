//
// api_test.cc
// api
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#include "ccgonowdep/api/native/ccgonowdep.h"

#include <string>

#include "gtest/gtest.h"

namespace ccgonowdep {

TEST(ApiTest, testDebuglog) {
  ccgonowdep_SetDebugLog(true);
  // TODO: add test code here
  ASSERT_TRUE(1 == 1);
}

}  // namespace ccgonowdep
