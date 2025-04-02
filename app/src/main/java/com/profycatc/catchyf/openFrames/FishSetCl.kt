package com.profycatc.catchyf.openFrames

import android.app.Application
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase

class FishSetCl:Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.analytics
        FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = true
    }
}