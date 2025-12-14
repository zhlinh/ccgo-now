Pod::Spec.new do |s|
  s.name             = 'ccgonow'
  s.version          = '1.0.0'
  s.summary          = 'ccgonow library'
  s.homepage         = 'https://github.com/zhlinh/ccgo-now'
  s.license          = { :type => 'MIT' }
  s.author           = 'zhlinh'
  s.source           = { :git => 'https://github.com/zhlinh/ccgo-now.git', :tag => 'v' + s.version.to_s }
  s.ios.deployment_target = '13.0'
  s.macos.deployment_target = '10.15'
  s.tvos.deployment_target = '13.0'
  s.watchos.deployment_target = '6.0'
  s.swift_version    = '5.0'
  s.vendored_frameworks = 'ccgonow.xcframework'
  s.libraries        = 'c++'
  s.pod_target_xcconfig = {
    'CLANG_CXX_LANGUAGE_STANDARD' => 'c++17',
    'CLANG_CXX_LIBRARY' => 'libc++',
  }
  s.static_framework = true
end
