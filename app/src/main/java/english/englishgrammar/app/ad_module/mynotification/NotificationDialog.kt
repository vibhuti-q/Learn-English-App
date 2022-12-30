package english.englishgrammar.app.mynotification

import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import english.englishgrammar.app.R


class NotificationDialog(
    var activity: Activity,
    private var imageUrl: String?,
    private var text1S: String?,
    private var text2S: String?,
    private var buttonText: String?,
    private var buttonActionUrl: String?
) : Dialog(activity) {

    private var onButtonClick: OnButtonClick? = null

    interface OnButtonClick {
        fun onPositiveButtonClick(link:String?)

        fun onNegativeButtonClick()
    }

    fun setListener(onButtonClick: OnButtonClick?) {
        this.onButtonClick = onButtonClick
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_notification)

        val textview1:AppCompatTextView=findViewById(R.id.text1T)
        val textview2:AppCompatTextView=findViewById(R.id.text2T)
        val positiveButton: Button =findViewById(R.id.positive_button)
        val closeImage:AppCompatImageView=findViewById(R.id.closeImage)
        val mainImage:AppCompatImageView=findViewById(R.id.notificationImage)
        when {
            text1S != null ->
            {
                textview1.visibility=View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    textview1.text = Html.fromHtml(text1S, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    textview1.text = Html.fromHtml(text1S)
                }
            }
            else->{
                textview1.visibility=View.GONE
            }
        }

        when {
            text2S != null ->
            {
                textview2.visibility=View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    textview2.text = Html.fromHtml(text2S, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    textview2.text = Html.fromHtml(text2S)
                }
            }
            else->{
                textview2.visibility=View.GONE
            }
        }

        when {
            imageUrl != null ->
            {
                mainImage.visibility=View.VISIBLE
                Glide.with(activity)
                    .load(imageUrl)
                    .placeholder(R.color.grey)
                    .error(R.color.black)
                    .priority(Priority.IMMEDIATE)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mainImage)
            }
            else->{
                mainImage.visibility=View.GONE
            }
        }

        closeImage.setOnClickListener { dismiss() }


        positiveButton.text=buttonText
        positiveButton.setOnClickListener { onButtonClick!!.onPositiveButtonClick(buttonActionUrl) }

    }

}
