package english.englishgrammar.app.ad_module.jaky_lxi.core_lxi

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import english.englishgrammar.app.databinding.ActivityWeMovedLxiBinding
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager

class LxiWeMovedActivity : AppCompatActivity() {
    private lateinit var bindinglxi: ActivityWeMovedLxiBinding
    var admobObjEverylxi: NaxleApps? = null
    lateinit var prefenrencMyPreferenceManagerlxi: LxiMyPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindinglxi = ActivityWeMovedLxiBinding.inflate(layoutInflater)
        setContentView(bindinglxi.root)

        val urldude = intent.getStringExtra("movedURL")
        val messagedude = intent.getStringExtra("movedDescription")
        if (!messagedude.isNullOrEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                bindinglxi.descriptionlxi.text = Html.fromHtml(messagedude, Html.FROM_HTML_MODE_LEGACY)
            } else
                bindinglxi.descriptionlxi.text = messagedude
        }

        bindinglxi.btnChecklxi.setOnClickListener {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urldude)))
            } catch (e: Exception) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urldude)))
                e.printStackTrace()
            }
        }

        prefenrencMyPreferenceManagerlxi = LxiMyPreferenceManager(this@LxiWeMovedActivity)
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