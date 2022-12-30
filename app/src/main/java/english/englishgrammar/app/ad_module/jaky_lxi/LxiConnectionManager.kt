package english.englishgrammar.app.jaky_lxi

import android.content.Context
import android.net.ConnectivityManager

class LxiConnectionManager(mContext: Context) {
    private var mContextlxi: Context = mContext

    fun isConnectedlxi(): Boolean {
        val connectivitylxi = mContextlxi.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfolxi = connectivitylxi.allNetworkInfo

        for (netInfo in networkInfolxi) {
            if (netInfo.typeName.equals("WIFI", ignoreCase = true)) for (i in networkInfolxi.indices) {
                if (netInfo.isConnected) return true
            }
            if (netInfo.typeName.equals("MOBILE", ignoreCase = true)) if (netInfo.isConnected) return true
        }
        return false
    }
}