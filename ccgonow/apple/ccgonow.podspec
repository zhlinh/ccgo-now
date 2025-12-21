Pod::Spec.new do |s|
  s.name             = 'ccgonow'
  s.version          = '1.0.2'
  s.summary          = 'ccgonow library'
  s.homepage         = 'https://github.com/zhlinh/ccgo-now'
  s.license          = { :type => 'MIT', :file => 'LICENSE' }
  s.author           = 'zhlinh'
  # Binary distribution: download xcframework zip from GitHub releases
  s.source           = { :http => 'https://github.com/zhlinh/ccgo-now/releases/download/v' + s.version.to_s + '/ccgonow.xcframework.zip' }
  # For git source (requires xcframework committed to repo):
  # s.source           = { :git => 'https://github.com/zhlinh/ccgo-now.git', :tag => 'v' + s.version.to_s }
  s.ios.deployment_target = '13.0'
  s.osx.deployment_target = '10.15'
  s.tvos.deployment_target = '13.0'
  s.watchos.deployment_target = '6.0'
  s.swift_version    = '5.0'
  s.ios.vendored_frameworks = 'ios/ccgonow.xcframework'
  s.osx.vendored_frameworks = 'macos/ccgonow.framework'
  s.tvos.vendored_frameworks = 'tvos/ccgonow.xcframework'
  s.watchos.vendored_frameworks = 'watchos/ccgonow.xcframework'
  s.libraries        = 'c++'
  s.pod_target_xcconfig = {
    'CLANG_CXX_LANGUAGE_STANDARD' => 'c++17',
    'CLANG_CXX_LIBRARY' => 'libc++',
  }
  s.static_framework = true

  # Dependencies
  s.dependency 'ccgonowdep', '~> 1.0'
end