package english.englishgrammar.app.jaky_lxi.utils_lxi

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LxiMyPreferenceManager @Inject constructor(@ApplicationContext private val mcontext: Context?) {

    var fixtime = 86400000L // 24 hr into millisecond
    var fixtime60 = 60000L // 1 m into millisecond
    var intervalTIme = 1200000L

    val sharedPreflxi =
        mcontext!!.getSharedPreferences(LxiCommon.PREFNAMEslxi, Context.MODE_PRIVATE)
    private val editorlxi = sharedPreflxi.edit()

    private val BANNERlxi = "banner"
    private val INTERSTITALlxi = "interstital"
    private val NADIDlxi = "NADID"
    private val OPENADlxi = "openad"
    private val RIDlxi = "rid"
    private val INTERVAL = "interval"
    private val RANDOMERM = "randomErm"
    private val RANDOMOTHER = "randomOther"
    private val LASTTIMING = "lastTiming"
    private val APPPID = "apppid"//usr
    private val SSL = "SSLSHA"
    private val SSLURL = "SSLURL"
    private val TALL = "tAll"
    private val TSTART = "tStart"
    private val OURL = "ourl"

    private val PRIVACYPOLICYlxi = "privacyPolicy"
    private val TERMSlxi = "termsandcondition"
    private val MOREAPPSlxi = "moreApps"
    private val SHOWCOUNT = "showCounters"
    private val INSTALLTIME = "installtime"
    private val DYNAMICDAYS = "dynamicDays"
    private val DYNAMICSHOWS = "dynamicShows"
    private val HOMEADSHOW = "homeAdShow"

    var privacyPolicylxi: String?
        get() = sharedPreflxi.getString(PRIVACYPOLICYlxi, "http://www.google.com")
        set(setprivacyPolicy) = storeStringlxi(PRIVACYPOLICYlxi, setprivacyPolicy)

    var termsandConditionlxi: String?
        get() = sharedPreflxi.getString(TERMSlxi, "http://www.google.com")
        set(setTermsandCondition) = storeStringlxi(TERMSlxi, setTermsandCondition)

    var moreAppslxi: String?
        get() = sharedPreflxi.getString(
            MOREAPPSlxi,
            "https://play.google.com/store/apps/developer?id="
        )
        set(setMoreApps) = storeStringlxi(MOREAPPSlxi, setMoreApps)

    var showCount: Int
        get() = sharedPreflxi.getInt(SHOWCOUNT, 0)
        set(setCount) {
            val editorLxi = sharedPreflxi.edit()
            editorLxi.putInt(SHOWCOUNT, setCount)
            editorLxi.apply()
        }

    var installTime: Long
        get() = sharedPreflxi.getLong(INSTALLTIME, 0)
        set(setInstallTime) {
            val editor1 = sharedPreflxi.edit()
            editor1.putLong(INSTALLTIME, setInstallTime)
            editor1.apply()
        }
    var dynamicDays: Int
        get() = sharedPreflxi.getInt(DYNAMICDAYS, 0)
        set(setDynamicDays) {
            val editor1 = sharedPreflxi.edit()
            editor1.putInt(DYNAMICDAYS, setDynamicDays)
            editor1.apply()
        }

    var dynamicShows: Boolean
        get() = sharedPreflxi.getBoolean(DYNAMICSHOWS, false)
        set(setDynamicShows) {
            val editor1 = sharedPreflxi.edit()
            editor1.putBoolean(DYNAMICSHOWS, setDynamicShows)
            editor1.apply()
        }


    var homeAdShow: Boolean
        get() = sharedPreflxi.getBoolean(HOMEADSHOW, false)
        set(setHomeAdShow) {
            val editor1 = sharedPreflxi.edit()
            editor1.putBoolean(HOMEADSHOW, setHomeAdShow)
            editor1.apply()
        }

    private fun storeStringlxi(keylxi: String, valuelxi: String?) {
        editorlxi.run {
            putString(keylxi, valuelxi)
            apply()
        }
    }

    private fun storeLonglxi(keylxi: String, valuelxi: Long) {
        editorlxi.run {
            putLong(keylxi, valuelxi)
            apply()
        }
    }

    private fun storeIntlxi(keylxi: String, valuelxi: Int) {
        editorlxi.run {
            putInt(keylxi, valuelxi)
            apply()
        }
    }

    private fun storeBooleanlxi(keylxi: String, valuelxi: Boolean) =
        editorlxi.run {
            putBoolean(keylxi, valuelxi)
            apply()
        }

    private fun getStringlxi(keyslxi: String) =
        sharedPreflxi.getString(keyslxi, "")

    var apppId: String?
        get() = sharedPreflxi.getString(APPPID, null)
        set(setUserId) = storeStringlxi(APPPID, setUserId)

    //region for use available count
    var isAppUpdate: Boolean
        get() = sharedPreflxi.getBoolean("isAppUpdate", true)
        set(setisUpdate) {
            val editor1 = sharedPreflxi.edit()
            editor1.putBoolean("isAppUpdate", setisUpdate)
            editor1.apply()
        }

    //endregion

    var lastTime: Long
        get() = sharedPreflxi.getLong(LASTTIMING, 0)
        set(setLastTime) {
            val editor1 = sharedPreflxi.edit()
            editor1.putLong(LASTTIMING, setLastTime)
            editor1.apply()
        }

    fun verifyInstallerIdlxi(contextlxi: Context): Boolean {
        // A list with valid installers package name
        val validInstallerslxi: List<String> =
            java.util.ArrayList(Arrays.asList("com.android.vending", "com.google.android.feedback"))

        // The package name of the app that has installed your app
        val installerdude =
            contextlxi.packageManager.getInstallerPackageName(contextlxi.packageName)

        // true if your app has been downloaded from Play Store
//        return installer != null && validInstallers.contains(installer)
        return true;
    }

    fun setfIdlxi(idlxi: String?) {
        storeStringlxi(INTERSTITALlxi, idlxi)
    }

    fun fIdlxi(): String? {
        return if (verifyInstallerIdlxi(mcontext!!)) {
            if (sharedPreflxi.getString(INTERSTITALlxi, "NA") != null) sharedPreflxi.getString(
                INTERSTITALlxi,
                "NA"
            ) else "NA"
        } else {
            return "NA"
        }
    }

    fun setopenIdlxi(idlxi: String?) {
        storeStringlxi(OPENADlxi, idlxi)
    }

    fun openIdlxi(): String? {
        return if (verifyInstallerIdlxi(mcontext!!)) {
            if (sharedPreflxi.getString(OPENADlxi, "NA") != null) sharedPreflxi.getString(
                OPENADlxi,
                "NA"
            ) else "NA"
        } else "NA"
    }

    fun setGBannerIDlxi(idlxi: String?) {
        storeStringlxi(BANNERlxi, idlxi)
    }

    fun getGBannerIDlxi(): String? {
        return if (verifyInstallerIdlxi(mcontext!!)) {
            if (sharedPreflxi.getString(BANNERlxi, "NA") != null) sharedPreflxi.getString(
                BANNERlxi,
                "NA"
            ) else "NA"
        } else "NA"
    }

    fun setrIdlxi(idlxi: String?) {
        storeStringlxi(RIDlxi, idlxi)
    }

    fun rIdlxi(): String? {
        return if (verifyInstallerIdlxi(mcontext!!)) {
            if (sharedPreflxi.getString(RIDlxi, "NA") != null) sharedPreflxi.getString(
                RIDlxi,
                "NA"
            ) else "NA"
        } else "NA"
    }

    fun setnadIdlxi(idslxi: String?) {
        storeStringlxi(NADIDlxi, idslxi)
    }

    fun nadIdlxi(): String? {
        return if (verifyInstallerIdlxi(mcontext!!)) {
            if (sharedPreflxi.getString(NADIDlxi, "NA") != null) sharedPreflxi.getString(
                NADIDlxi,
                "NA"
            ) else "NA"
        } else "NA"
    }

    //region set last interstital display
    fun checkLastTimeShow(): Boolean {
        val cget = sharedPreflxi.getLong("last_updateTimeShow", 0)
        val time = System.currentTimeMillis()
        val fix = sharedPreflxi.getLong(INTERVAL, 0)
        val diff = time - cget
        return if (diff == 0L || diff > fix) {
            val editor = sharedPreflxi.edit()
            editor.putLong("last_updateTimeShow", 0)
            editor.apply()
            true
        } else false
    }

    fun setLastTimeShow() {
        val time = System.currentTimeMillis()
        val editor1 = sharedPreflxi.edit()
        editor1.putLong("last_updateTimeShow", time)
        editor1.commit()
    }

    //endregion
    //region interstital google
    fun checkTimeI(): Boolean {
        val cget = sharedPreflxi.getLong("last_updateTime", 0)
        val time = System.currentTimeMillis()
        val diff = time - cget
        return if (diff == 0L || diff > fixtime60) {
            val editor = sharedPreflxi.edit()
            editor.putLong("last_updateTime", 0)
            editor.apply()
            true
        } else false
    }

    fun setLastTime() {
        val time = System.currentTimeMillis()
        val editor = sharedPreflxi.edit()
        editor.putLong("last_updateTime", time)
        editor.apply()

    }

    fun checkTimeI24(): Boolean {
        val cget = sharedPreflxi.getLong("last_updateTime24", 0)
        val time = System.currentTimeMillis()
        val diff = time - cget
        return if (diff == 0L || diff > fixtime) {
            val editor = sharedPreflxi.edit()
            editor.putLong("last_updateTime24", 0)
            editor.apply()
            true
        } else false
    }

    fun setLastTime24() {
        val time = System.currentTimeMillis()
        val editor = sharedPreflxi.edit()
        editor.putLong("last_updateTime24", time)
        editor.apply()

    }

    //endregion
    //region google banner
    fun checkTimeB(): Boolean {
        val cget = sharedPreflxi.getLong("last_updateTimeB", 0)
        val time = System.currentTimeMillis()
        val diff = time - cget
        return if (diff == 0L || diff > fixtime60) {
            val editor = sharedPreflxi.edit()
            editor.putLong("last_updateTimeB", 0)
            editor.apply()
            Log.e("bbb2", "fgfh")
            true
        } else {
            Log.e("bbb3", "fgfh")
            false
        }
    }

    fun setLastTimeB() {
        val time = System.currentTimeMillis()
        val editor = sharedPreflxi.edit()
        editor.putLong("last_updateTimeB", time)
        editor.apply()
    }

    fun setLastTimeB24() {
        val time = System.currentTimeMillis()
        val editor = sharedPreflxi.edit()
        editor.putLong("last_updateTimeB24", time)
        editor.apply()
    }

    fun checkTimeB24(): Boolean {
        val cget = sharedPreflxi.getLong("last_updateTimeB24", 0)
        val time = System.currentTimeMillis()
        val diff = time - cget
        return if (diff == 0L || diff > fixtime) {
            val editor = sharedPreflxi.edit()
            editor.putLong("last_updateTimeB24", 0)
            editor.apply()
            Log.e("bbb2", "fgfh")
            true
        } else {
            Log.e("bbb3", "fgfh")
            false
        }
    }


    //endregion
    //region natv google
    fun checkTimenatvvideo(): Boolean {
        val cget = sharedPreflxi.getLong("last_updateTimenatv", 0)
        val time = System.currentTimeMillis()
        val diff = time - cget
        return if (diff == 0L || diff > fixtime60) {
            val editor = sharedPreflxi.edit()
            editor.putLong("last_updateTimenatv", 0)
            editor.apply()
            true
        } else false
    }

    fun setnatvvideoLastTime() {
        val time = System.currentTimeMillis()
        val editor = sharedPreflxi.edit()
        editor.putLong("last_updateTimenatv", time)
        editor.apply()
    }

    fun checkTimenatvvideo24(): Boolean {
        val cget = sharedPreflxi.getLong("last_updateTimenatv24", 0)
        val time = System.currentTimeMillis()
        val diff = time - cget
        return if (diff == 0L || diff > fixtime) {
            val editor = sharedPreflxi.edit()
            editor.putLong("last_updateTimenatv24", 0)
            editor.apply()
            true
        } else false
    }

    fun setnatvvideoLastTime24() {
        val time = System.currentTimeMillis()
        val editor = sharedPreflxi.edit()
        editor.putLong("last_updateTimenatv24", time)
        editor.apply()
    }
    //endregion

    //region openad google
    fun checkTimeopenad(): Boolean {
        val cget = sharedPreflxi.getLong("last_updateTimenaOpenad", 0)
        val time = System.currentTimeMillis()
        val diff = time - cget
        return if (diff == 0L || diff > fixtime) {
            val editor = sharedPreflxi.edit()
            editor.putLong("last_updateTimenaOpenad", 0)
            editor.apply()
            true
        } else false
    }

    fun setopenadLastTime() {
        val time = System.currentTimeMillis()
        val editor = sharedPreflxi.edit()
        editor.putLong("last_updateTimenaOpenad", time)
        editor.apply()
    }

    //endregion


    fun getRandomErm(): Int {
        return sharedPreflxi.getInt(RANDOMERM, 3)
    }

    fun setRandomOther(id: Int) {
        storeIntlxi(RANDOMOTHER, id)
    }

    fun getRandomOther(): Int {
        return sharedPreflxi.getInt(RANDOMOTHER, 3)
    }

    fun randomNum(): Int {
        if (!apppId.isNullOrEmpty()) {
//            Logger.e("---hu em user")
            return getRandomErm()
        }
//        Logger.e("---nathi re baba")
        return getRandomOther()
    }

    val randomNum: Int
        get() {
            val rand = Random()
            return rand.nextInt(randomNum() - 1 + 1) + 1
        }
}