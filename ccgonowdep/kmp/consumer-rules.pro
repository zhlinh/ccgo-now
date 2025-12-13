# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep native method names for JNI
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep CcgonowdepKMP public API
-keep class com.mojeter.ccgo.ccgonowdep.kmp.** { *; }
