package english.englishgrammar.app.ad_module;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import english.englishgrammar.app.LxiMyApplication;
import english.englishgrammar.app.MainActivity;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

public class LxiAppOpenManager implements Application.ActivityLifecycleCallbacks, LifecycleObserver {
    private static final String LOG_TAGlxi = "AppOpenManager";
    private AppOpenAd appOpenAdlxi = null;
    private AppOpenAd.AppOpenAdLoadCallback loadCallbacklxi;
    private Activity currentActivitylxi;
    private static boolean isShowingAdlxi = false;
    private final LxiMyApplication myApplicationlxi;

    public LxiAppOpenManager(LxiMyApplication myApplication) {
        this.myApplicationlxi = myApplication;
        this.myApplicationlxi.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public void fetchAdlxi() {
        // Have unused ad, no need to fetch another.
        if (isAdAvailablelxi()) {
            return;
        }

        loadCallbacklxi =
                new AppOpenAd.AppOpenAdLoadCallback() {
                    /**
                     * Called when an app open ad has loaded.
                     *
                     * @param ad the loaded app open ad.
                     */
                    @Override
                    public void onAppOpenAdLoaded(AppOpenAd ad) {
                        LxiAppOpenManager.this.appOpenAdlxi = ad;
                    }

                    /**
                     * Called when an app open ad has failed to load.
                     *
                     * @param loadAdError the error.
                     */
                    @Override
                    public void onAppOpenAdFailedToLoad(LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(LOG_TAGlxi, "error in loading");
                    }
                };
        AdRequest requestslxi = getAdRequestlxi();

        LxiMyPreferenceManager myPreferenceManagerslxi = new LxiMyPreferenceManager(currentActivitylxi);
        AppOpenAd.load(myApplicationlxi, myPreferenceManagerslxi.openIdlxi() + "", requestslxi,
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallbacklxi);
    }

    public void showAdIfAvailablelxi() {
        if (!isShowingAdlxi && isAdAvailablelxi()) {
            Log.d(LOG_TAGlxi, "Will show ad.");

            FullScreenContentCallback fullScreenContentCallbacklxi =
                    new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Set the reference to null so isAdAvailable() returns false.
                            LxiAppOpenManager.this.appOpenAdlxi = null;
                            isShowingAdlxi = false;
                            fetchAdlxi();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            isShowingAdlxi = true;
                        }
                    };

            appOpenAdlxi.show(currentActivitylxi, fullScreenContentCallbacklxi);

        } else {
            Log.d(LOG_TAGlxi, "Can not show ad.");
            fetchAdlxi();
        }
    }

    private AdRequest getAdRequestlxi() {
        return new AdRequest.Builder().build();
    }

    public boolean isAdAvailablelxi() {
        return appOpenAdlxi != null;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        currentActivitylxi = activity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        currentActivitylxi = activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        currentActivitylxi = null;
    }

    @OnLifecycleEvent(ON_START)
    public void onStart() {
        if (currentActivitylxi instanceof MainActivity)
            showAdIfAvailablelxi();

        Log.i("currentActivity", currentActivitylxi + "");
    }
}