package com.luizgadao.testzup;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.luizgadao.testzup.model.Movie;
import com.luizgadao.testzup.model.SearchMovies;
import com.squareup.otto.Bus;

import java.util.ArrayList;

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

    public void addMovie( Movie movie ){
        ArrayList<Movie> movies = getMoviesSaved();

        movies.add( 0, movie );

        SearchMovies searchMovies = new SearchMovies();
        searchMovies.setMovies( movies );

        String strJson = new Gson().toJson( searchMovies );
        saveJsonPreferences( strJson );
    }

    public ArrayList<Movie> getMovies(){
        return getMoviesSaved();
    }

    private SharedPreferences getSharedPreferences(){
        return getSharedPreferences( "my_preferences", Context.MODE_PRIVATE );
    }

    private void saveJsonPreferences( String stringJson )
    {
        getSharedPreferences().edit()
                .putString( "data", stringJson )
                .commit();
    }

    private ArrayList<Movie> getMoviesSaved(){
        String strJson = getSharedPreferences().getString( "data", "-" );
        SearchMovies searchMovies = new SearchMovies();
        if ( strJson.equals( "-" ) )
            searchMovies.setMovies( new ArrayList<Movie>() );
        else
            searchMovies = new Gson().fromJson( strJson, SearchMovies.class );

        return searchMovies.getMovies();
    }
}
