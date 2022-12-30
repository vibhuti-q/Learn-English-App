package english.englishgrammar.app.jaky_lxi.mains_lxi


import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.orhanobut.logger.Logger
import english.englishgrammar.app.R
import english.englishgrammar.app.jaky_lxi.dialogs_lxi.LxiAdShowingDialog
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdLoadListener
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager
import english.englishgrammar.app.jaky_lxi.utils_lxi.beVisiblelxi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NaxleApps {

    fun showAdAfter(context: Activity, closeEventSTED: LxiadmobCloseEvent, adId: String?, dc: Int) {
        count += 1
        try {
            if (count > dc) {
                count = 0
                Logger.e("showI->")
                showAdDailog(context)
                if (!isNetworkAvailable(context)) {
                    Logger.e("return")
                    dismmisEverything(context, closeEventSTED)
                    return
                }
                if (mInterstitialAd == null) {
                    Logger.e("return null")
                    dismmisEverythingWithLoad(context, closeEventSTED, adId)
                    return
                }
                if (!context.isFinishing) {
                    Logger.e("isFinishing->")
                    if (mInterstitialAd != null && mInterstitialAd!!.isLoaded) {
                        Logger.e("show->")
                        GlobalScope.launch {
                            delay(500)
                            context.runOnUiThread {
                                LxiMyPreferenceManager(context).setLastTimeShow()
                                dismisAdDialog(context)
                                if (mInterstitialAd != null) {

                                    mInterstitialAd!!.show()
                                    mInterstitialAd!!.adListener = object : AdListener() {
                                        override fun onAdLoaded() {
                                            super.onAdLoaded()
                                        }

                                        override fun onAdClosed() {
                                            super.onAdClosed()
                                            Logger.e("onAdClosed->")
                                            dismmisEverythingWithLoad(context, closeEventSTED, adId)
                                        }

                                        override fun onAdFailedToLoad(i: Int) {
                                            super.onAdFailedToLoad(i)
                                            Logger.e("onAdFailedToLoad->")
                                            dismmisEverything(context, closeEventSTED)
                                        }

                                        override fun onAdLeftApplication() {
                                            Logger.e("onAdLeftApplication->")
                                        }

                                        override fun onAdClicked() {
                                            super.onAdClicked()
                                            Logger.e("onAdClicked")
                                        }
                                    }
                                } else {
                                    dismmisEverythingWithLoad(context, closeEventSTED, adId)
                                }
                            }
                        }

                    } else {
//                        Logger.e("loadednot->")
                        dismmisEverythingWithLoad(context, closeEventSTED, adId)
                    }
                } else {
                    dismmisEverything(context, closeEventSTED)
                }
            } else {
//                Logger.e("finisf->")
                dismmisEverything(context, closeEventSTED)
            }
        } catch (e: Exception) {
            Logger.e("Exception->")
            dismmisEverythingWithLoad(context, closeEventSTED, adId)
        }
    }

    fun showIntrestrialAdslxi(
        context: Activity,
        closeEventSTED: LxiadmobCloseEvent,
        adId: String?
    ) {
        val millisInDay = (1000 * 60 * 60 * 24).toLong()

        val dcFinal = LxiMyPreferenceManager(context).showCount
        val dc = 0
        val dynamicDays = LxiMyPreferenceManager(context).dynamicDays //2
        val dynamicShows = LxiMyPreferenceManager(context).dynamicShows//true
        val firstTime = LxiMyPreferenceManager(context).installTime//install time

        val currentTime = System.currentTimeMillis()
        val diffrance = currentTime - firstTime
        val diffranceFinal = diffrance / millisInDay

        if (dynamicShows) {

            if (diffranceFinal >= dynamicDays) {
                // normal
                showAdAfter(context, closeEventSTED, adId, dcFinal)
            } else {
                // everytime
                showAdAfter(context, closeEventSTED, adId, dc)
            }
        } else {
            if (diffranceFinal >= dynamicDays) {
                // everytime
                showAdAfter(context, closeEventSTED, adId, dc)
            } else {
                // normal
                showAdAfter(context, closeEventSTED, adId, dcFinal)
            }
        }
    }

    fun showAdInHomeScreen(context: Activity, closeEventSTED: LxiadmobCloseEvent, adId: String?) {
        try {

            if (!LxiMyPreferenceManager(context).homeAdShow) {
                closeEventSTED.setAdmobCloseEventlxi()
                return
            }

            if (adId == null) {
                closeEventSTED.setAdmobCloseEventlxi()
                return
            }

            val adShowDiloagH = LxiAdShowingDialog(context, context.getString(R.string.showingad))
            adShowDiloagH!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            adShowDiloagH!!.setCanceledOnTouchOutside(false)
            adShowDiloagH.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (!context.isFinishing && adShowDiloagH != null) {
                adShowDiloagH.show()
            }
            if (!isNetworkAvailable(context)) {
                if (!context.isFinishing && adShowDiloagH.isShowing) {
                    adShowDiloagH.cancel()
                }
                closeEventSTED.setAdmobCloseEventlxi()
                return
            }
            if (mInterstitialAd == null) {
                GlobalScope.launch {
                    delay(5000)
                    showHaD(context, adShowDiloagH, closeEventSTED, adId)
                }
            } else {
                if (!context.isFinishing) {
                    GlobalScope.launch {
                        delay(500)
                        context.runOnUiThread {
                            if (mInterstitialAd?.isLoaded == true) {
                                showHaD(context, adShowDiloagH, closeEventSTED, adId)
                            } else {
                                GlobalScope.launch {
                                    delay(4500)
                                    showHaD(context, adShowDiloagH, closeEventSTED, adId)
                                }
                            }
                        }
                    }
                } else {
                    if (!context.isFinishing && adShowDiloagH.isShowing) {
                        adShowDiloagH.cancel()
                    }
                    closeEventSTED.setAdmobCloseEventlxi()
                }
            }
        } catch (e: Exception) {
            closeEventSTED.setAdmobCloseEventlxi()
        }
    }

    fun showHaD(
        context: Activity,
        adShowDiloagH: LxiAdShowingDialog,
        closeEventSTED: LxiadmobCloseEvent,
        adId: String?
    ) {
        context.runOnUiThread {
            if (!context.isFinishing && adShowDiloagH.isShowing) {
                adShowDiloagH.cancel()
            }
            if (mInterstitialAd != null && mInterstitialAd?.isLoaded == true) {

                mInterstitialAd!!.show()
                mInterstitialAd!!.adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        super.onAdLoaded()
                    }

                    override fun onAdClosed() {
                        super.onAdClosed()
                        if (!context.isFinishing && adShowDiloagH.isShowing) {
                            adShowDiloagH.cancel()
                        }
                        closeEventSTED.setAdmobCloseEventlxi()
                        loadInterstitialAdsdude(context, adId)
                    }

                    override fun onAdFailedToLoad(i: Int) {
                        super.onAdFailedToLoad(i)
                        if (!context.isFinishing && adShowDiloagH.isShowing) {
                            adShowDiloagH.cancel()
                        }
                        closeEventSTED.setAdmobCloseEventlxi()

                    }
                }
            } else {
                if (!context.isFinishing && adShowDiloagH.isShowing) {
                    adShowDiloagH.cancel()
                }
                loadInterstitialAdsdude(context, adId)
                closeEventSTED.setAdmobCloseEventlxi()
            }
        }
    }

    fun dismmisEverything(context: Activity, closeEventSTED: LxiadmobCloseEvent) {
        dismisAdDialog(context)
        closeEventSTED.setAdmobCloseEventlxi()
    }

    fun dismmisEverythingWithLoad(
        context: Activity,
        closeEventSTED: LxiadmobCloseEvent,
        adId: String?
    ) {
        dismisAdDialog(context)
        closeEventSTED.setAdmobCloseEventlxi()
        loadInterstitialAdsdude(context, adId)
    }

    fun showAdDailog(context: Activity) {
        if (!context.isFinishing && adShowDiloagSTED != null) {
            adShowDiloagSTED.show()
        }
    }

    fun dismisAdDialog(context: Activity) {
        if (!context.isFinishing && adShowDiloagSTED != null && adShowDiloagSTED.isShowing) {
            adShowDiloagSTED.cancel()
        }
    }

    /** Which layout
     * @author Hp
     * @param which = 1 - R.layout.native_single_start
     * @param which = 2 - R.layout.native_g_dialog
     * @param which = 3 - R.layout.native_single_gad_unified
     * @param which = 4 - R.layout.native_single_gad_vd
     */
    fun showAndLoadGoogleNativelxi(
        activitylxi: Activity?,
        nadIdlxi: String?,
        nativeAdContainerGlxi: FrameLayout?,
        wantToShow: Boolean = true,
        which: Int,
        nativeEventlxi: LxiNativeAdListener

    ) {
        nativeAdContainer = nativeAdContainerGlxi

        if (gnativeAd != null) {
            if (nativeAdContainerGlxi != null) {
                if (wantToShow) {
                    try {
                        if (activitylxi != null) {
                            showNativeGoogle(
                                activitylxi,
                                nativeAdContainerGlxi,
                                which,
                                nativeEventlxi
                            )
                            loadGoogleNativelxi(activitylxi, nadIdlxi, null)
                        } else {
                            nativeEventlxi.setNativeFailedlxi()
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                        nativeEventlxi.setNativeFailedlxi()
                    }
                } else {
                    loadGoogleNativelxi(activitylxi, nadIdlxi, object : LxiNativeAdLoadListener {
                        override fun setNativeSuccesslxi() {
                        }

                        override fun setNativeFailedlxi() {
                        }
                    })
                }
            } else {
                nativeEventlxi.setNativeFailedlxi()
            }

        } else {
            loadGoogleNativelxi(activitylxi, nadIdlxi, object : LxiNativeAdLoadListener {
                override fun setNativeSuccesslxi() {
                    if (activitylxi != null && nativeAdContainerGlxi != null) {
                        showNativeGoogle(activitylxi, nativeAdContainerGlxi, which, nativeEventlxi)
                        loadGoogleNativelxi(activitylxi, nadIdlxi, null)
                    } else {
                        nativeEventlxi.setNativeFailedlxi()
                    }
                }

                override fun setNativeFailedlxi() {
                    nativeEventlxi.setNativeFailedlxi()
                }
            })
        }

    }

    fun showNativeGoogle(
        activity: Activity,
        nativeAdContainerG: FrameLayout,
        which: Int,
        nativeEventSTED: LxiNativeAdListener
    ) {
        when (which) {
            1 -> {
                nativeAdContainerG.beVisiblelxi()
                Logger.e("native Show-> 1")
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_single_start_lxi,
                    null
                ) as UnifiedNativeAdView
//                    adView.isHardwareAccelerated
                LxiNativeGoogle().populateUnifiedNativeAdViewStartlxi(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccesslxi()
            }
            2 -> {
                nativeAdContainerG.beVisiblelxi()
                Logger.e("native Show-> 2")
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_g_dialog_lxi,
                    null
                ) as UnifiedNativeAdView
//                    adView.isHardwareAccelerated
                LxiNativeGoogle().populateUnifiedNativeAdViewDlxi(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccesslxi()
            }
            3 -> {
                nativeAdContainerG.beVisiblelxi()
                Logger.e("native Show-> 3")
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_single_gad_unified_lxi,
                    null
                ) as UnifiedNativeAdView
//                    adView.isHardwareAccelerated
                LxiNativeGoogle().populateUnifiedNativeAdViewUnifilxi(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccesslxi()
            }
            4 -> {
                nativeAdContainerG.beVisiblelxi()
                Logger.e("native Show-> 4")
                val adView = activity.layoutInflater.inflate(
                    R.layout.native_single_gad_vd_lxi,
                    null
                ) as UnifiedNativeAdView
//                    adView.isHardwareAccelerated
                LxiNativeGoogle().populateUnifiedNativeAdViewUnifilxi(gnativeAd, adView)
                nativeAdContainerG.removeAllViews()
                nativeAdContainerG.addView(adView)
                nativeEventSTED.setNativeSuccesslxi()

            }
        }
    }

    fun showBannerAdlxi(
        activity: Activity,
        closeEventSTED: LxiadmobCloseEvent,
        BannerID: String?,
        adContainer: LinearLayout,
    ) {
        try {
            if (mAdView != null) {
                adContainer.visibility = View.VISIBLE
//                Logger.e("adContainer ---")
                if (adContainer.childCount > 0)
                    adContainer.removeAllViews()

                adContainer.addView(mAdView)

                mAdView!!.adListener = object : AdListener() {
                    override fun onAdFailedToLoad(i: LoadAdError) {
                        super.onAdFailedToLoad(i)
                        closeEventSTED.setFailedlxi()
                        onLoadagainBanner(activity, closeEventSTED, BannerID)
                    }

                    override fun onAdClosed() {
                        super.onAdClosed()
                        Logger.e("onAdClosed ---")
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        closeEventSTED.setSuccesslxi()
                        Logger.e("onAdLoaded --- Baner")
                    }

                    override fun onAdLeftApplication() {
                        super.onAdLeftApplication()
                    }
                }
                onLoadagainBanner(activity, closeEventSTED, BannerID)

            } else {
                Logger.e("no adContainer ---")
                onLoadagainBanner(activity, closeEventSTED, BannerID)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            onLoadagainBanner(activity, closeEventSTED, BannerID)
        }
    }

    fun onLoadagainBanner(
        activity: Activity,
        closeEventSTED: LxiadmobCloseEvent,
        BannerID: String?
    ) {
        closeEventSTED.setAdmobCloseEventlxi()
        loadbannerlxi(activity, BannerID, closeEventSTED)
    }


    companion object {
        var count = 0
        lateinit var adShowDiloagSTED: LxiAdShowingDialog
        var mInterstitialAd: InterstitialAd? = null
        var gnativeAd: UnifiedNativeAd? = null
        var mAdView: AdView? = null
        var nativeAdContainer: ViewGroup? = null

        @JvmStatic
        fun loadInterstitialAdsdude(activity: Activity, fId: String?) {
            if (fId != null && isNetworkAvailable(activity)) {
                Logger.e("why ?")
                nowLoadFull(activity, fId)
            } else {
                Logger.e("why not?")
                mInterstitialAd = null
            }
        }

        fun nowLoadFull(activity: Activity, fId: String?) {
            try {
                Logger.e("fId->")
                mInterstitialAd = InterstitialAd(activity)
                mInterstitialAd!!.adUnitId = fId
                mInterstitialAd!!.loadAd(
                    AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()
                )
                mInterstitialAd!!.adListener = object : AdListener() {
                    override fun onAdFailedToLoad(i: LoadAdError) {
                        super.onAdFailedToLoad(i)
                        Logger.e("Fload->${i.message}")
                        //Toast.makeText(context, "Fail to display ads", Toast.LENGTH_SHORT).show();
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        Logger.e("load->")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        fun setAdShowDialogslxi(context: Activity) {
            adShowDiloagSTED = LxiAdShowingDialog(context, context.getString(R.string.showingad))
            adShowDiloagSTED!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            adShowDiloagSTED!!.setCanceledOnTouchOutside(false)
            adShowDiloagSTED.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        fun loadGoogleNativelxi(
            activity: Activity?,
            nadId: String?,
            nativeAdLoadListenerSTED: LxiNativeAdLoadListener?
        ) {
            if (activity != null && nadId != null) {
                val builder = AdLoader.Builder(activity, nadId)
                builder.forUnifiedNativeAd { unifiedNativeAd ->
                    gnativeAd = unifiedNativeAd
                }
                val videoOptions = VideoOptions.Builder().setStartMuted(true).build()
                val adOptions = NativeAdOptions.Builder().setVideoOptions(videoOptions).build()
                builder.withNativeAdOptions(adOptions)
                val adLoader = builder.withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(errorCode: Int) {
                        Logger.e("native roror->$errorCode")
                        nativeAdLoadListenerSTED?.setNativeFailedlxi()
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        Logger.e("native success")
                        nativeAdLoadListenerSTED?.setNativeSuccesslxi()
                    }

                    override fun onAdClicked() {
                        super.onAdClicked()
                        Logger.e("native clickeds")

                    }
                }).build()

                adLoader.loadAd(AdRequest.Builder().build())
            } else {
                nativeAdLoadListenerSTED?.setNativeFailedlxi()
            }
        }

        fun loadbannerlxi(
            activity: Activity,
            BannerID: String?,
            closeEventSTED: LxiadmobCloseEvent?
        ) {
            if (!activity.isFinishing && BannerID != null) {
                mAdView = AdView(activity)

                mAdView!!.adSize = getAdSize(activity)
                mAdView!!.adUnitId = BannerID

                val adRequest = AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("9FC332F7CF9BF0E19E307ABB3A880E86").build()

                mAdView!!.loadAd(adRequest)
                mAdView!!.adListener = object : AdListener() {
                    override fun onAdFailedToLoad(i: LoadAdError) {
                        super.onAdFailedToLoad(i)
                        Logger.e("onAdFailedToLoad onAdFailedToLoad-${i.message}")
                        mAdView = null
                    }

                    override fun onAdClosed() {
                        super.onAdClosed()
                        Logger.e("onAdClosed ---")
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        closeEventSTED?.setSuccesslxi()
//                        Logger.e("onAdLoaded ---")
                    }
                }
            } else {
                mAdView = null
            }

        }


        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
        }

        private fun getAdSize(activityNow: Activity): AdSize? {
            // Step 2 - Determine the screen width (less decorations) to use for the ad width.
            val display: Display = activityNow.windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val widthPixels = outMetrics.widthPixels.toFloat()
            val density = outMetrics.density

            val adWidth = (widthPixels / density).toInt()

            // Step 3 - Get adaptive ad size and return for setting on the ad view.
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activityNow, adWidth)
        }

    }
}