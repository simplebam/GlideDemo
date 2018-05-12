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
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# qiniu
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}
-ignorewarnings


#glide4.0 https://www.jianshu.com/p/f1f17f816d1d
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# for DexGuard only
-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

# 从glide4.0开始，GifDrawable没有提供getDecoder()方法，
# 需要通过反射获取gifDecoder字段值，所以需要保持GifFrameLoader和GifState类不被混淆
-keep class com.bumptech.glide.load.resource.gif.GifDrawable$GifState{*;}
-keep class com.bumptech.glide.load.resource.gif.GifFrameLoader {*;}
