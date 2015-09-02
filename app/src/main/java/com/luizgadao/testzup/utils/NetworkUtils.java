package com.luizgadao.testzup.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by luizcarlos on 01/09/15.
 */
public class NetworkUtils {

    public static boolean isNetworkAvailable( Context context )
    {
        ConnectivityManager connectivityManager = ( ConnectivityManager ) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if ( info != null && info.isConnected() )
            return true;

        return false;
    }

}
