package english.englishgrammar.app.ad_module.jaky_lxi.core_lxi

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import english.englishgrammar.app.databinding.ActivityMaintenanceLxiBinding
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager

class LxiMaintenanceActivity : AppCompatActivity() {
    private lateinit var bindinglxi: ActivityMaintenanceLxiBinding
    var admobObjEverylxi: NaxleApps? = null
    lateinit var prefenrencMyPreferenceManagerlxi: LxiMyPreferenceManager

    override fun onCreate(savedInstanceStatedude: Bundle?) {
        super.onCreate(savedInstanceStatedude)
        bindinglxi = ActivityMaintenanceLxiBinding.inflate(layoutInflater)
        setContentView(bindinglxi.root)

        val messagedude = intent.getStringExtra("maintainceMessage")
        if (!messagedude.isNullOrEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                bindinglxi.descriptionlxi.text = Html.fromHtml(messagedude, Html.FROM_HTML_MODE_LEGACY)
            } else
                bindinglxi.descriptionlxi.text = messagedude
        }

        bindinglxi.btnContactlxi.setOnClickListener {
            finish()
        }

        prefenrencMyPreferenceManagerlxi = LxiMyPreferenceManager(this@LxiMaintenanceActivity)
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
                }

                override fun setNativeFailedlxi() {
                    bindinglxi.nadViewlxi.relaNativ.visibility = View.GONE
                }
            })
    }
}