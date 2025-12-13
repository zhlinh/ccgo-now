//
// api_test.cc
// api
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#include "ccgonow/api/native/ccgonow.h"

#include <string>

#include "gtest/gtest.h"

namespace ccgonow {

TEST(ApiTest, testDebuglog) {
  ccgonow_SetDebugLog(true);
  // TODO: add test code here
  ASSERT_TRUE(1 == 1);
}

}  // namespace ccgonow
