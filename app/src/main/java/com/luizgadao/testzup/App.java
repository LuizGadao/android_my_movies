package com.luizgadao.testzup;

import android.app.Application;

import com.luizgadao.testzup.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luizcarlos on 25/08/15.
 */
public class App extends Application {

    private static App app;
    private List<Movie> movies;

    @Override
    public void onCreate() {
        super.onCreate();

        app = new App();
    }

    public static App getInstance(){
        return app;
    }

    public boolean addMovie( Movie movie ){
        if ( movies == null )
            movies = new ArrayList<Movie>();

        movies.add( 0, movie );
        return true;
    }

    public List<Movie> getMovies(){ return movies; }
}
