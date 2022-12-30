package english.englishgrammar.app.jaky_lxi.mains_lxi

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import english.englishgrammar.app.R

class LxiNativeGoogle {

    fun populateUnifiedNativeAdViewDlxi(nativeAdslxi: UnifiedNativeAd?, adViewslxi: UnifiedNativeAdView) {
        if (nativeAdslxi!=null) {// Set the media view.
            adViewslxi.mediaView = adViewslxi.findViewById<com.google.android.gms.ads.formats.MediaView>(R.id.ad_medialxi)

            // Set other ad assets.
            adViewslxi.headlineView = adViewslxi.findViewById(R.id.ad_headlinelxi)
            adViewslxi.bodyView = adViewslxi.findViewById(R.id.ad_bodylxi)
            adViewslxi.iconView = adViewslxi.findViewById(R.id.ad_app_iconlxi)
            adViewslxi.starRatingView = adViewslxi.findViewById(R.id.ad_starslxi)
            adViewslxi.advertiserView = adViewslxi.findViewById(R.id.ad_advertiserlxi)

            // The headline and media content are guaranteed to be in every UnifiedNativeAd.
            (adViewslxi.headlineView as TextView).text = nativeAdslxi!!.headline
            adViewslxi.mediaView.setMediaContent(nativeAdslxi!!.mediaContent)

            // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
            // check before trying to display them.
            if (nativeAdslxi!!.body == null) {
                adViewslxi.bodyView.visibility = View.INVISIBLE
            } else {
                adViewslxi.bodyView.visibility = View.VISIBLE
                (adViewslxi.bodyView as TextView).text = nativeAdslxi!!.body
            }

            if (nativeAdslxi!!.icon == null) {
                adViewslxi.iconView.visibility = View.GONE
            } else {
                (adViewslxi.iconView as ImageView).setImageDrawable(
                        nativeAdslxi!!.icon.drawable)
                adViewslxi.iconView.visibility = View.VISIBLE
            }

            if (nativeAdslxi!!.starRating == null) {
                adViewslxi.starRatingView.visibility = View.INVISIBLE
            } else {
                (adViewslxi.starRatingView as RatingBar).rating = nativeAdslxi!!.starRating!!.toFloat()
                adViewslxi.starRatingView.visibility = View.VISIBLE
            }

            if (nativeAdslxi!!.advertiser == null) {
                adViewslxi.advertiserView.visibility = View.INVISIBLE
            } else {
                (adViewslxi.advertiserView as TextView).text = nativeAdslxi!!.advertiser
                adViewslxi.advertiserView.visibility = View.VISIBLE
            }

            // This method tells the Google Mobile Ads SDK that you have finished populating your
            // native ad view with this native ad.
            adViewslxi.setNativeAd(nativeAdslxi)
        }
    }

    fun populateUnifiedNativeAdViewStartlxi(nativeAdslxi: UnifiedNativeAd?, adViewlxi: UnifiedNativeAdView) {

        if (nativeAdslxi!=null)  {// Set the media view.
            adViewlxi.mediaView = adViewlxi.findViewById<com.google.android.gms.ads.formats.MediaView>(R.id.ad_medialxi)

            // Set other ad assets.
            adViewlxi.headlineView = adViewlxi.findViewById(R.id.ad_headlinelxi)
            adViewlxi.callToActionView = adViewlxi.findViewById(R.id.ad_call_to_actionlxi)
            adViewlxi.iconView = adViewlxi.findViewById(R.id.ad_app_iconlxi)
            adViewlxi.priceView = adViewlxi.findViewById(R.id.ad_pricelxi)
            adViewlxi.starRatingView = adViewlxi.findViewById(R.id.ad_starslxi)
            adViewlxi.storeView = adViewlxi.findViewById(R.id.ad_storelxi)
            adViewlxi.advertiserView = adViewlxi.findViewById(R.id.ad_advertiserlxi)

            // The headline and media content are guaranteed to be in every UnifiedNativeAd.
            (adViewlxi.headlineView as TextView).text = nativeAdslxi!!.headline
            adViewlxi.mediaView.setMediaContent(nativeAdslxi!!.mediaContent)

            if (nativeAdslxi!!.callToAction == null) {
                adViewlxi.callToActionView.visibility = View.GONE
            } else {
                adViewlxi.callToActionView.visibility = View.VISIBLE
                (adViewlxi.callToActionView as Button).text = nativeAdslxi!!.callToAction
            }

            if (nativeAdslxi!!.icon == null) {
                adViewlxi.iconView.visibility = View.GONE
            } else {
                (adViewlxi.iconView as ImageView).setImageDrawable(
                        nativeAdslxi!!.icon.drawable)
                adViewlxi.iconView.visibility = View.VISIBLE
            }

            if (nativeAdslxi!!.price == null) {
                adViewlxi.priceView.visibility = View.GONE
            } else {
                adViewlxi.priceView.visibility = View.VISIBLE
                (adViewlxi.priceView as TextView).text = nativeAdslxi!!.price
            }

            if (nativeAdslxi!!.store == null) {
                adViewlxi.storeView.visibility = View.GONE
            } else {
                adViewlxi.storeView.visibility = View.VISIBLE
                (adViewlxi.storeView as TextView).text = nativeAdslxi!!.store
            }

            if (nativeAdslxi!!.starRating == null) {
                adViewlxi.starRatingView.visibility = View.INVISIBLE
            } else {
                (adViewlxi.starRatingView as RatingBar).rating = nativeAdslxi!!.starRating!!.toFloat()
                adViewlxi.starRatingView.visibility = View.VISIBLE
            }

            if (nativeAdslxi!!.advertiser == null) {
                adViewlxi.advertiserView.visibility = View.GONE
            } else {
                (adViewlxi.advertiserView as TextView).text = nativeAdslxi!!.advertiser
                adViewlxi.advertiserView.visibility = View.VISIBLE
            }

            // This method tells the Google Mobile Ads SDK that you have finished populating your
            // native ad view with this native ad.
            adViewlxi.setNativeAd(nativeAdslxi)
        }

    }

     fun populateUnifiedNativeAdViewUnifilxi(nativeAdslxi: UnifiedNativeAd?, adViewlxi: UnifiedNativeAdView) {

        if (nativeAdslxi!=null) {
            adViewlxi.mediaView = adViewlxi.findViewById<com.google.android.gms.ads.formats.MediaView>(R.id.ad_medialxi)

            // Set other ad assets.
            adViewlxi.headlineView = adViewlxi.findViewById(R.id.ad_headlinelxi)
            adViewlxi.bodyView = adViewlxi.findViewById(R.id.ad_bodylxi)
            adViewlxi.callToActionView = adViewlxi.findViewById(R.id.ad_call_to_actionlxi)
            adViewlxi.iconView = adViewlxi.findViewById(R.id.ad_app_iconlxi)
            adViewlxi.priceView = adViewlxi.findViewById(R.id.ad_pricelxi)
            adViewlxi.starRatingView = adViewlxi.findViewById(R.id.ad_starslxi)
            adViewlxi.storeView = adViewlxi.findViewById(R.id.ad_storelxi)
            adViewlxi.advertiserView = adViewlxi.findViewById(R.id.ad_advertiserlxi)

            // The headline and media content are guaranteed to be in every UnifiedNativeAd.
            (adViewlxi.headlineView as TextView).text = nativeAdslxi.headline
            adViewlxi.mediaView.setMediaContent(nativeAdslxi.mediaContent)

            // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
            // check before trying to display them.
            if (nativeAdslxi.body == null) {
                adViewlxi.bodyView.visibility = View.GONE
            } else {
                adViewlxi.bodyView.visibility = View.GONE
                (adViewlxi.bodyView as TextView).text = nativeAdslxi.body
            }

            if (nativeAdslxi.callToAction == null) {
                adViewlxi.callToActionView.visibility = View.INVISIBLE
            } else {
                adViewlxi.callToActionView.visibility = View.VISIBLE
                (adViewlxi.callToActionView as Button).text = nativeAdslxi.callToAction
            }

            if (nativeAdslxi.icon == null) {
                adViewlxi.iconView.visibility = View.GONE
            } else {
                (adViewlxi.iconView as ImageView).setImageDrawable(
                        nativeAdslxi.icon.drawable)
                adViewlxi.iconView.visibility = View.VISIBLE
            }

            if (nativeAdslxi.price == null) {
                adViewlxi.priceView.visibility = View.INVISIBLE
            } else {
                adViewlxi.priceView.visibility = View.VISIBLE
                (adViewlxi.priceView as TextView).text = nativeAdslxi.price
            }

            if (nativeAdslxi.store == null) {
                adViewlxi.storeView.visibility = View.INVISIBLE
            } else {
                adViewlxi.storeView.visibility = View.VISIBLE
                (adViewlxi.storeView as TextView).text = nativeAdslxi.store
            }

            if (nativeAdslxi.starRating == null) {
                adViewlxi.starRatingView.visibility = View.INVISIBLE
            } else {
                (adViewlxi.starRatingView as RatingBar).rating = nativeAdslxi.starRating!!.toFloat()
                adViewlxi.starRatingView.visibility = View.VISIBLE
            }

            if (nativeAdslxi.advertiser == null) {
                adViewlxi.advertiserView.visibility = View.INVISIBLE
            } else {
                (adViewlxi.advertiserView as TextView).text = nativeAdslxi.advertiser
                adViewlxi.advertiserView.visibility = View.VISIBLE
            }

            // This method tells the Google Mobile Ads SDK that you have finished populating your
            // native ad view with this native ad.
            adViewlxi.setNativeAd(nativeAdslxi)
        }

    }

}