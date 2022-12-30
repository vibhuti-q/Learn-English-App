package english.englishgrammar.app.jaky_lxi.utils_lxi

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.telephony.PhoneNumberUtils
import android.widget.Toast
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps

object LxiCommon {
    const val TOURTIMElxi = 10000
    val TAGlxi = "Bhavin->"
    const val fixtime5mlxi = 300000L
    const val RESULT_FORCED_CANCELlxi = 24

    @JvmField
    var PREFNAMEslxi = "JAKKAASS"

    fun killMelxi(activitylxi: Activity) {
        activitylxi.finishAffinity()
    }

    fun setUpadDialoglxi(activitylxi: Activity) {
        try {
            NaxleApps.setAdShowDialogslxi(activitylxi)
        } catch (e: Exception) {
        }
    }

    fun getAIdlxi(activitylxi: Context): String? {
        return Settings.Secure.getString(activitylxi.getContentResolver(), Settings.Secure.ANDROID_ID)
    }

    fun whatsApplxi(mobileNumberwtsaplxi: String?, contextdude: Context) {
        val isWhatsappInstalledlxi = wappInstalledOrNotlxi("com.whatsapp", contextdude)
        if (isWhatsappInstalledlxi) {
            val sendIntentlxi = Intent("android.intent.action.MAIN")
            sendIntentlxi.component =
                ComponentName("com.whatsapp", "com.whatsapp.Conversation")
            sendIntentlxi.putExtra(
                "jid", PhoneNumberUtils.stripSeparators(
                    mobileNumberwtsaplxi
                ) + "@s.whatsapp.net"
            ) //phone number without "+" prefix
            contextdude.startActivity(sendIntentlxi)
        } else {
            val urilxi = Uri.parse("market://details?id=com.whatsapp")
            val goToMarketlxi = Intent(Intent.ACTION_VIEW, urilxi)
            Toast.makeText(
                contextdude, "WhatsApp not Installed",
                Toast.LENGTH_SHORT
            ).show()
            contextdude.startActivity(goToMarketlxi)
        }
    }

    private fun wappInstalledOrNotlxi(targetPackageslxi: String, contextslxi: Context): Boolean {
        val pmlxi = contextslxi.packageManager
        try {
            val infolxi = pmlxi.getPackageInfo(targetPackageslxi, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
        return true
    }
}