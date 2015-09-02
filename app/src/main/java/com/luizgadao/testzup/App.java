package com.luizgadao.testzup;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by luizcarlos on 25/08/15.
 */
public class App extends Application {

    private static App app;
    private Bus bus;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        bus = new Bus();
    }

    public static App getInstance(){
        return app;
    }

    public Bus getBus() {
        return bus;
    }

}
