package english.englishgrammar.app.jaky_lxi.utils_lxi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor

fun Context.getMyDrawablelxi(idlxi : Int) : Drawable?{

    return  ContextCompat.getDrawable(this, idlxi)
}
fun View.beInvisibleIflxi(beInvisiblelxi: Boolean) = if (beInvisiblelxi) beInvisiblelxi() else beVisiblelxi()

fun View.beVisibleIflxi(beVisiblelxi: Boolean) = if (beVisiblelxi) beVisiblelxi() else beGonelxi()

fun View.beGoneIflxi(beGonelxi: Boolean) = beVisibleIflxi(!beGonelxi)

fun View.beInvisiblelxi() {
    visibility = View.INVISIBLE
}
fun View.beGonelxi(){
    visibility = View.GONE
}
fun View.beVisiblelxi(){
    visibility = View.VISIBLE
}
fun View.isVisiblelxi() = visibility == View.VISIBLE

fun View.isInvisiblelxi() = visibility == View.INVISIBLE

fun View.isGonelxi() = visibility == View.GONE

fun Context.getColorMylxi(@ColorRes colorlxi: Int): Int{
    return ContextCompat.getColor(this, colorlxi)
}

fun TextView.setDrawableColorlxi(@ColorRes colorlxi: Int) {
    compoundDrawables.filterNotNull().forEach {
        it.colorFilter = PorterDuffColorFilter(getColor(this.context, colorlxi), PorterDuff.Mode.SRC_IN)
    }
}

fun TextView.setTexttColorlxi(@ColorRes colorlxi: Int) {
    setTextColor(ContextCompat.getColor(this.context, colorlxi))
}
fun TextView.setTexttColorNlxi(colorlxi: Int) {
    setTextColor(colorlxi)
}
fun TextView.setDrawableColorNlxi(colorlxi: Int) {
    compoundDrawables.filterNotNull().forEach {
        it.colorFilter = PorterDuffColorFilter(colorlxi, PorterDuff.Mode.SRC_IN)
    }
}


fun ImageView.loadImagesWithGlideExtDrawlxi(drawablelxi: Int) {
    setImageResource(drawablelxi)
}

fun Int.dplxi(): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()
}

fun Context.themeColornewlxi(@AttrRes attrReslxi: Int): Int {
    val typedValuelxi = TypedValue()
    theme.resolveAttribute (attrReslxi, typedValuelxi, true)
    return typedValuelxi.data
}

inline fun <reified T : Activity> Context.Navigate(blocklxi: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(blocklxi))
}


fun Activity.mtoastlxi(messagelxi: CharSequence, durationlxi: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, messagelxi, durationlxi).show()
}