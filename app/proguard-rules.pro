# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

# Keeps necessary for the app to work
# classes may also be annotated "kept", instead of here listed
#-keep class com.android.skeleton.ClassX

# Print remove code, entry points.
-printusage "/home/user/AndroidStudioProjects/Skeleton/app/build/outputs/mapping/release/usage.txt"
-printseeds "/home/user/AndroidStudioProjects/Skeleton/app/build/outputs/mapping/release/seeds.txt"
