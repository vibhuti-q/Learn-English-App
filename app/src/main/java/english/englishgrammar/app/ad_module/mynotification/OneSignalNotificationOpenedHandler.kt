package english.englishgrammar.app.mynotification

import android.app.Application
import android.content.Context
import android.content.Intent
import com.onesignal.OSNotificationOpenedResult
import com.onesignal.OneSignal
import english.englishgrammar.app.LxiMyApplication.Companion.buttonActionUrl
import english.englishgrammar.app.LxiMyApplication.Companion.buttonText
import english.englishgrammar.app.LxiMyApplication.Companion.imageUrl
import english.englishgrammar.app.LxiMyApplication.Companion.isInAppMessage
import english.englishgrammar.app.LxiMyApplication.Companion.text1
import english.englishgrammar.app.LxiMyApplication.Companion.text2
import english.englishgrammar.app.R
import english.englishgrammar.app.ad_module.jaky_lxi.core_lxi.LxiSplashTESActivity


class OneSignalNotificationOpenedHandler(private val context: Application) :
    OneSignal.OSNotificationOpenedHandler {

    override fun notificationOpened(result: OSNotificationOpenedResult) {
        val data = result.notification.additionalData
        if (data != null && data.length() > 0) {
            var imageUrlJ: String? = null
            var text1J: String? = null
            var text2J: String? = null
            var buttonTextJ: String? = null
            var buttonActionUrlJ: String? = null

            val preferences = context.getSharedPreferences(
                context.getString(R.string.app_name) + "qrlitescan",
                Context.MODE_PRIVATE
            )
            val editor = preferences.edit()
            editor.putBoolean(isInAppMessage, true).apply()

            if (data.has(imageUrl)) {
                imageUrlJ = data.getString(imageUrl)
                editor.putString(imageUrl, imageUrlJ).apply()
            }
            if (data.has(text1)) {
                text1J = data.getString(text1)
                editor.putString(text1, text1J).apply()
            }
            if (data.has(text2)) {
                text2J = data.getString(text2)
                editor.putString(text2, text2J).apply()
            }
            if (data.has(buttonText)) {
                buttonTextJ = data.getString(buttonText)
                editor.putString(buttonText, buttonTextJ).apply()
            }
            if (data.has(buttonActionUrl)) {
                buttonActionUrlJ = data.getString(buttonActionUrl)
                editor.putString(buttonActionUrl, buttonActionUrlJ).apply()
            }

            val intent = Intent(context, LxiSplashTESActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } else {

            val intent = Intent(context, LxiSplashTESActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}