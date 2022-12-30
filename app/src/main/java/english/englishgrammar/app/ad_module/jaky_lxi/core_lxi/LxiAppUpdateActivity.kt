package english.englishgrammar.app.ad_module.jaky_lxi.core_lxi

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import english.englishgrammar.app.databinding.ActivityAppUpdateLxiBinding
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager

class LxiAppUpdateActivity : AppCompatActivity() {
    private lateinit var bindinglxi: ActivityAppUpdateLxiBinding
    var admobObjEverylxi: NaxleApps? = null
    lateinit var prefenrencMyPreferenceManagerlxi: LxiMyPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindinglxi = ActivityAppUpdateLxiBinding.inflate(layoutInflater)
        setContentView(bindinglxi.root)

        val messagedude = intent.getStringExtra("versionMessage")
        if (!messagedude.isNullOrEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                bindinglxi.updateDescriptionlxi.text =
                    Html.fromHtml(messagedude, Html.FROM_HTML_MODE_LEGACY)
            } else
                bindinglxi.updateDescriptionlxi.text = messagedude
        }

        bindinglxi.btnUpdatelxi.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$packageName")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }

        prefenrencMyPreferenceManagerlxi = LxiMyPreferenceManager(this@LxiAppUpdateActivity)
        admobObjEverylxi = NaxleApps()
        observerViewModelslxi()
    }

    fun observerViewModelslxi() {
        admobObjEverylxi?.showAndLoadGoogleNativelxi(activitylxi = this,
            nadIdlxi = prefenrencMyPreferenceManagerlxi.nadIdlxi(),
            nativeAdContainerGlxi = bindinglxi.nadViewlxi.containerlxi,
            which = 1,
            nativeEventlxi = object : LxiNativeAdListener {
                override fun setNativeSuccesslxi() {
                    bindinglxi.nadViewlxi.relaNativ.visibility = View.VISIBLE
                }
                override fun setNativeFailedlxi() {
                    bindinglxi.nadViewlxi.relaNativ.visibility = View.GONE
                }
            })
    }
}