package com.luizgadao.testzup;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.luizgadao.testzup.model.Movie;
import com.luizgadao.testzup.model.MovieDetails;
import com.luizgadao.testzup.model.MoviesDetailsSaved;
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
        saveJsonPreferences( "data", strJson );
    }

    public void addMovieDetails( MovieDetails movieDetails ){
        ArrayList<MovieDetails> moviesDetailsSaved = getMoviesDetailsSaved();
        moviesDetailsSaved.add( 0, movieDetails );

        MoviesDetailsSaved listSaved = new MoviesDetailsSaved();
        listSaved.setMoviesSaved( moviesDetailsSaved );

        saveJsonPreferences( "data-details", new Gson().toJson( listSaved ) );
    }

    public ArrayList<Movie> getMovies(){
        return getMoviesSaved();
    }

    private SharedPreferences getSharedPreferences(){
        return getSharedPreferences( "my_preferences", Context.MODE_PRIVATE );
    }

    private void saveJsonPreferences( String key, String stringJson )
    {
        getSharedPreferences().edit()
                .putString( key, stringJson )
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

    private ArrayList<MovieDetails> getMoviesDetailsSaved(){
        String strJson = getSharedPreferences().getString( "data-details", "-" );
        ArrayList<MovieDetails> moviesDetailsSaved = new ArrayList<>();
        if ( strJson.equals( "-" ) == false )
            moviesDetailsSaved = new Gson().fromJson( strJson, MoviesDetailsSaved.class ).getMoviesSaved();

        return moviesDetailsSaved;
    }

    public MovieDetails getMovieDetails( Movie movie ){
        ArrayList<MovieDetails> moviesDetailsSaved = getMoviesDetailsSaved();

        int len = moviesDetailsSaved.size();
        for ( int i = 0; i < len; i++ ){
            if ( movie.imdbID.equals( moviesDetailsSaved.get( i ).imdbID ) )
                return moviesDetailsSaved.get( i );
        }

        return null;
    }
}
