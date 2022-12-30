package english.englishgrammar.app.ad_module.jaky_lxi.utils_lxi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class LxiConnectionDetector {

    private Context _contextlxi;

    public LxiConnectionDetector(Context context) {
        this._contextlxi = context;
    }

    public boolean isConnectingToInternetlxi() {
        ConnectivityManager connectivitylxi = (ConnectivityManager) _contextlxi.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfolxi = connectivitylxi.getAllNetworkInfo();

        for (NetworkInfo netInfolxi : networkInfolxi) {

            if (netInfolxi.getTypeName().equalsIgnoreCase("WIFI"))

                for (int i = 0; i < networkInfolxi.length; i++) {
                    if (netInfolxi.isConnected())

                        return true;
                }
            if (netInfolxi.getTypeName().equalsIgnoreCase("MOBILE"))

                if (netInfolxi.isConnected())

                    return true;
        }
        return false;
    }

}

