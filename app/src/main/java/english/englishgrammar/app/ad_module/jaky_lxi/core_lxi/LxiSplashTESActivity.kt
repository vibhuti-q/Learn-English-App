package english.englishgrammar.app.ad_module.jaky_lxi.core_lxi

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import english.englishgrammar.app.BuildConfig
import english.englishgrammar.app.MainActivity

import english.englishgrammar.app.jaky_lxi.LxiApiUtils
import english.englishgrammar.app.jaky_lxi.LxiConnectionManager
import english.englishgrammar.app.jaky_lxi.LxiSettingsModel
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager
import english.englishgrammar.app.databinding.ActivitySplashLxiBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class LxiSplashTESActivity : AppCompatActivity() {
    var isStartScreenlxi = false;
    private lateinit var bindinglxi: ActivitySplashLxiBinding
    lateinit var prefenrencMyPreferenceManagerlxi: LxiMyPreferenceManager

    external fun stringFromC(): String

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isTaskRoot
            && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
            && intent.action != null
            && intent.action.equals(Intent.ACTION_MAIN)
        ) {
            finish()
            return
        }
        bindinglxi = ActivitySplashLxiBinding.inflate(layoutInflater)
        setContentView(bindinglxi.root)

        prefenrencMyPreferenceManagerlxi = LxiMyPreferenceManager(this@LxiSplashTESActivity)
        val cdlxi = LxiConnectionManager(this@LxiSplashTESActivity)
        if (cdlxi.isConnectedlxi()) {
            getFromRestAPIslxi()
        } else {
            setNullIdslxi()
            goHomeslxi()
        }
    }

    private fun getFromRestAPIslxi() {
        val BASE_URLlxi = stringFromC()

        val clientlxi: OkHttpClient = if (BuildConfig.DEBUG) {
            val interceptorlxi = HttpLoggingInterceptor()
            interceptorlxi.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(interceptorlxi) // remove when live it's for log
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES).build()
        } else {
            OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES).build()
        }
        val retrofitslxi: Retrofit = Retrofit.Builder().baseUrl(BASE_URLlxi)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientlxi)
            .build()

        val myApilxi = retrofitslxi.create(LxiApiUtils::class.java)

        val pkglxi = packageName.replace(".", "_") + ".json"
        val calllxi: Call<LxiSettingsModel> = myApilxi.getPowerslxi(pkglxi)
        calllxi.enqueue(object : Callback<LxiSettingsModel> {
            override fun onResponse(
                call: Call<LxiSettingsModel>?,
                response: retrofit2.Response<LxiSettingsModel>
            ) {
                val settingModel: LxiSettingsModel? = response.body()

                if (settingModel != null) {
                    prefenrencMyPreferenceManagerlxi.setGBannerIDlxi(settingModel?.googleBannerlxi)
                    prefenrencMyPreferenceManagerlxi.setfIdlxi(settingModel?.googleInterstitiallxi)
                    prefenrencMyPreferenceManagerlxi.setnadIdlxi(settingModel?.googleNativelxi)
                    prefenrencMyPreferenceManagerlxi.setrIdlxi(settingModel?.googleRewardedlxi)
                    prefenrencMyPreferenceManagerlxi.setopenIdlxi(settingModel?.googleOpenAdIdlxi)

                    ////region setting api

                    prefenrencMyPreferenceManagerlxi.dynamicShows = settingModel.dynamicShows
                    prefenrencMyPreferenceManagerlxi.dynamicDays = settingModel.dynamicDays
                    if (prefenrencMyPreferenceManagerlxi.installTime <= 0) {
                        prefenrencMyPreferenceManagerlxi.installTime = System.currentTimeMillis()
                    }

                    prefenrencMyPreferenceManagerlxi.homeAdShow = settingModel.homeAdShow

                    prefenrencMyPreferenceManagerlxi.privacyPolicylxi =
                        settingModel?.privacyPolicylxi
                    prefenrencMyPreferenceManagerlxi.termsandConditionlxi =
                        settingModel?.termsandconditionlxi
                    prefenrencMyPreferenceManagerlxi.moreAppslxi = settingModel?.moreAppslxi
                    prefenrencMyPreferenceManagerlxi.showCount = settingModel?.showCount!!

                    isStartScreenlxi = settingModel?.isStartScreenlxi == true

                    val appCurrentVersionslxi: Long =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                            packageManager.getPackageInfo(packageName, 0).longVersionCode
                        else
                            packageManager.getPackageInfo(packageName, 0).versionCode.toLong()

                    val appWebVersionlxi = settingModel?.versionlxi ?: 1

                    NaxleApps.loadGoogleNativelxi(
                        this@LxiSplashTESActivity,
                        prefenrencMyPreferenceManagerlxi.nadIdlxi(),
                        null
                    )
                    NaxleApps.loadbannerlxi(
                        this@LxiSplashTESActivity,
                        prefenrencMyPreferenceManagerlxi.getGBannerIDlxi(),
                        null
                    )
                    NaxleApps.loadInterstitialAdsdude(
                        this@LxiSplashTESActivity,
                        prefenrencMyPreferenceManagerlxi.fIdlxi()
                    )

                    when {
                        appWebVersionlxi > appCurrentVersionslxi -> {
                            val updateApplxi =
                                Intent(this@LxiSplashTESActivity, LxiAppUpdateActivity::class.java)
                            settingModel.versionMessagelxi?.let {
                                updateApplxi.putExtra("versionMessage", it)
                            }
                            startActivity(updateApplxi)
                            finish()
                        }
                        settingModel.isMaintaincelxi -> {
                            val maintenanceslxi =
                                Intent(
                                    this@LxiSplashTESActivity,
                                    LxiMaintenanceActivity::class.java
                                )
                            settingModel.maintainceMessagelxi?.let {
                                maintenanceslxi.putExtra("maintainceMessage", it)
                            }
                            startActivity(maintenanceslxi)
                            finish()
                        }
                        settingModel.isMovedlxi -> {
                            val movedlxi =
                                Intent(this@LxiSplashTESActivity, LxiWeMovedActivity::class.java)
                            settingModel.movedURLlxi?.let {
                                movedlxi.putExtra("movedURL", it)
                                movedlxi.putExtra(
                                    "movedDescription",
                                    settingModel.movedDescriptionlxi
                                )
                            }
                            startActivity(movedlxi)
                            finish()
                        }
                        else -> {
                            goHomeslxi()
                        }
                    }
                } else {
                    goHomeslxi()
                }
            }

            override fun onFailure(call: Call<LxiSettingsModel>?, t: Throwable?) {
                setNullIdslxi()
                goHomeslxi()
            }
        })
    }

    fun setNullIdslxi() {
        prefenrencMyPreferenceManagerlxi.setGBannerIDlxi(null)
        prefenrencMyPreferenceManagerlxi.setfIdlxi(null)
        prefenrencMyPreferenceManagerlxi.setnadIdlxi(null)
        prefenrencMyPreferenceManagerlxi.setrIdlxi(null)
        prefenrencMyPreferenceManagerlxi.setopenIdlxi(null)

    }

    private fun goHomeslxi() {
//        if (BuildConfig.DEBUG) {
        prefenrencMyPreferenceManagerlxi.setGBannerIDlxi("ca-app-pub-3940256099942544/6300978111")
        prefenrencMyPreferenceManagerlxi.setfIdlxi("ca-app-pub-3940256099942544/1033173712")
        prefenrencMyPreferenceManagerlxi.setnadIdlxi("ca-app-pub-3940256099942544/2247696110")
        prefenrencMyPreferenceManagerlxi.setrIdlxi("ca-app-pub-3940256099942544/5224354917")
        prefenrencMyPreferenceManagerlxi.setopenIdlxi("ca-app-pub-3940256099942544/3419835294")
//    }

//        if (isStartScreenlxi) {
//            startActivity(Intent(this@LxiSplashTESActivity, LxiStartActivity::class.java))
//            finishAffinity()
//        } else {
        startActivity(
            Intent(
                this@LxiSplashTESActivity,
                MainActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
        finishAffinity()
//        }
    }
}