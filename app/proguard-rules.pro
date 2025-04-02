# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {
java.lang.String getId();
boolean isLimitAdTrackingEnabled();}
-keep public class com.android.installreferrer.** {*;}
-keep class com.google.android.gms.common.ConnectionResult {
int SUCCESS;}
-keep public class com.miui.referrer.** {*;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {
com.google.android.gms.ads.identifier.AdvertisingIdClient$Info getAdvertisingIdInfo(android.content.Context);}
# If you keep the line number information, uncomment this to
-dontwarn com.ironsource.**
-dontwarn com.ironsource.adapters.**
-keepclassmembers class com.ironsource.** { public *; }
-keep public class com.ironsource.**
-keep class com.ironsource.adapters.** { *;
}