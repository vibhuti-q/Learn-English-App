package english.englishgrammar.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.google.android.gms.ads.MobileAds
import com.onesignal.OneSignal
import english.englishgrammar.app.ad_module.LxiAppOpenManager
import english.englishgrammar.app.ad_module.jaky_lxi.core_lxi.LxiSplashTESActivity
import english.englishgrammar.app.mynotification.MyNotificationWillShowInForegroundHandler
import english.englishgrammar.app.mynotification.OneSignalNotificationOpenedHandler

@SuppressLint("StaticFieldLeak")
class LxiMyApplication : MultiDexApplication() {

    private var currentActivity: Activity? = null

    companion object {
        lateinit var appInstance: LxiMyApplication
        const val ONESIGNAL_APP_ID = "1c7023a7-d42e-486b-b14a-b06b96283328"
        const val imageUrl = "imageUrl"
        const val text1 = "text1"
        const val text2 = "text2"
        const val buttonText = "buttonText"
        const val buttonActionUrl = "buttonActionUrl"
        const val isInAppMessage = "isInAppMessage"
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this@LxiMyApplication

        //region OneSignal
        if (BuildConfig.DEBUG) {
            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        }
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        OneSignal.setNotificationOpenedHandler(OneSignalNotificationOpenedHandler(this))
        OneSignal.setNotificationWillShowInForegroundHandler(
            MyNotificationWillShowInForegroundHandler(this)
        )
        //endregion

        MobileAds.initialize(this)
        val appOpenManagersjv = LxiAppOpenManager(this@LxiMyApplication)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


}