package com.bellminp.instrumentation

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.util.Log
import androidx.annotation.NonNull
import com.bellminp.common.timberMsg
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import kotlin.random.Random

@HiltAndroidApp
class InstrumentationApplication : Application(){

    companion object {
        lateinit var mInstance: InstrumentationApplication
        fun get(): InstrumentationApplication {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()

        mInstance = this
        setUpTimber()

    }

    private fun setUpTimber(){
        val pm = packageManager
        try {
            val appInfo = pm.getApplicationInfo(packageName, 0)
            if (0 != appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) {
                Timber.plant(Timber.DebugTree())
            } else {
                Timber.plant(CrashReportingTree())
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.plant(CrashReportingTree())
        }
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, @NonNull message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
        }
    }
}