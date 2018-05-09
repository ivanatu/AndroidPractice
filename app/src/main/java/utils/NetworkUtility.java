package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by baron on 26/03/2018.
 */


public class NetworkUtility {
     Context context;

    public NetworkUtility(Context context) {
        this.context = context;
    }

    public boolean isWifiConnected() {
            ConnectivityManager connMgr = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
    }
}
