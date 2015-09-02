package com.luizgadao.testzup.network;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.luizgadao.testzup.R;
import com.luizgadao.testzup.utils.NetworkUtils;

/**
 * Created by luizcarlos on 26/08/15.
 */
public class VolleyHelper {

    private  static VolleyHelper volley;
    private RequestQueue queue;
    private Context context;

    private VolleyHelper( Context context ){
        this.context = context;
        queue = getRequestQueue();
    }

    public static VolleyHelper getInstance(Context context){
        if ( volley == null )
            volley = new VolleyHelper( context );

        return volley;
    }

    private RequestQueue getRequestQueue() {
        if ( queue == null )
            queue = Volley.newRequestQueue( context.getApplicationContext() );

        return queue;
    }

    public <T> void addRequestQueue(Request<T> request, View view){
        if ( NetworkUtils.isNetworkAvailable( context ) ) {
            request.setRetryPolicy(
                    new DefaultRetryPolicy(
                            3 * 1000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT )
            );
            getRequestQueue().add( request );
        }
        else{
            Snackbar.make( view, context.getString( R.string.without_connection), Snackbar.LENGTH_INDEFINITE )
                .setAction( R.string.connection, new View.OnClickListener() {
                    @Override
                    public void onClick( View v ) {
                        Intent intent = new Intent( new Intent( Settings.ACTION_WIFI_SETTINGS ) );
                        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                        context.startActivity( intent );
                    }
                } )
                .show();
        }

    }

    public void removeRequestQueue( String tag ){
        getRequestQueue().cancelAll( tag );
    }

}
