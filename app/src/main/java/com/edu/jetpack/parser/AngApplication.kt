package com.edu.jetpack.parser

import android.content.Context
import androidx.multidex.MultiDexApplication
import androidx.work.Configuration
import androidx.work.WorkManager
import com.tencent.mmkv.MMKV
import com.edu.jetpack.parser.AppConfig.ANG_PACKAGE
import com.edu.jetpack.parser.handler.SettingsManager
import com.edu.jetpack.parser.util.LogUtil
import es.dmoral.toasty.Toasty

class AngApplication : MultiDexApplication() {
    companion object {
        lateinit var application: AngApplication
    }

    /**
     * Attaches the base context to the application.
     * @param base The base context.
     */
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        application = this
    }

    /**
     * Initializes the application.
     */
    override fun onCreate() {
        super.onCreate()

        MMKV.initialize(this)

        // Ensure critical preference defaults are present in MMKV early
        try {
            SettingsManager.initApp(this)
        } catch (e: Exception) {
            LogUtil.e("AngApplication", "SettingsManager init failed", e)
        }
        SettingsManager.setNightMode()

        Toasty.Config.getInstance()
            .setGravity(android.view.Gravity.BOTTOM, 0, 300)
            .apply()
    }
}