package com.prashant.androidcodingexercise.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


public class ConnectivityUtil {

    private Context _mContext;
    private final String TAG = ConnectivityUtil.class.getSimpleName();

    public ConnectivityUtil(Context context) {
        this._mContext = context;
    }

    // check if internet is available or not
    public boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) _mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();

        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return false;
    }

}
