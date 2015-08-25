package com.luizgadao.testzup;

import android.app.Application;

/**
 * Created by luizcarlos on 25/08/15.
 */
public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = new App();
    }

    public static App getInstance(){
        return app;
    }
}
