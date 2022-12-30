package english.englishgrammar.app.jaky_lxi.dialogs_lxi

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import english.englishgrammar.app.R

class LxiAdShowingDialog(var mContextlxi: Context, private var messagelxi: String?) : Dialog(mContextlxi) {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_loading_dialog_lxi)

        when {
            messagelxi != null -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.tvMessagelxi).text = Html.fromHtml(messagelxi, Html.FROM_HTML_MODE_LEGACY)
            } else {
                findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.tvMessagelxi).text = Html.fromHtml(messagelxi)
            }
        }
    }
}
