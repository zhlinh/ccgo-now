Pod::Spec.new do |s|
  s.name             = 'Ccgonow'
  s.version          = '1.0.1'
  s.summary          = 'ccgonow library'
  s.homepage         = 'https://github.com/zhlinh/ccgonow'
  s.license          = { :type => 'MIT', :file => 'LICENSE' }
  s.author           = 'zhlinh'
  # Binary distribution: download xcframework zip from GitHub releases
  s.source           = { :http => 'https://github.com/zhlinh/ccgo-now/releases/download/v' + s.version.to_s + '/Ccgonow.xcframework.zip' }
  # For git source (requires xcframework committed to repo):
  # s.source           = { :git => 'https://github.com/zhlinh/ccgo-now.git', :tag => 'v' + s.version.to_s }
  s.ios.deployment_target = '13.0'
  s.osx.deployment_target = '10.15'
  s.swift_version    = '5.0'
  s.ios.vendored_frameworks = 'ios/Ccgonow.xcframework'
  s.osx.vendored_frameworks = 'macos/Ccgonow.framework'
  s.libraries        = 'c++'
  s.pod_target_xcconfig = {
    'CLANG_CXX_LANGUAGE_STANDARD' => 'c++17',
    'CLANG_CXX_LIBRARY' => 'libc++',
  }
  s.static_framework = true

  # Dependencies
  s.dependency 'Ccgonowdep', '~> 1.0'
end