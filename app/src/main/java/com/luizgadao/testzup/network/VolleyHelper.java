package com.luizgadao.testzup.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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

    public <T> void addRequestQueu(Request<T> request){
        getRequestQueue().add( request );
    }

}
