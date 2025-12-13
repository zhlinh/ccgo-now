//
// all_bench.cc
// benches
//
// Created by ccgo on 2025-11-30.
// Copyright 2025 ccgo Project Authors. All rights reserved.

#include "benchmark/benchmark.h"

static void CheckDebugLog(int argc, char **argv) {
  // if has --debug in argv then open debug log
  bool is_debug = false;
  for (int i = 0; i < argc; ++i) {
    if (strcmp(argv[i], "--debuglog") == 0) {
      is_debug = true;
      break;
    }
  }
  if (is_debug) {
    // FIXME: add some work here
  }
}

int main(int argc, char **argv) {
  ::benchmark::Initialize(&argc, argv);

  CheckDebugLog(argc, argv);

  ::benchmark::RunSpecifiedBenchmarks();
  ::benchmark::Shutdown();
  return 0;
}
