package com.luizgadao.testzup.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.luizgadao.testzup.App;
import com.luizgadao.testzup.model.Movie;
import com.luizgadao.testzup.model.MovieDetails;
import com.luizgadao.testzup.model.MoviesDetailsSaved;
import com.luizgadao.testzup.model.SearchMovies;

import java.util.ArrayList;

/**
 * Created by luizcarlos on 02/09/15.
 */
public class SharePreferecesUtils {

    public static final String MOVIES = "movies";
    public static final String MOVIES_DETAILS = "movies-details";

    public SharePreferecesUtils() {
    }

    public ArrayList<Movie> getMovies(){
        return getMoviesSaved();
    }

    private SharedPreferences getSharedPreferences(){
        return App.getInstance()
                .getSharedPreferences( "my_preferences", Context.MODE_PRIVATE );
    }

    private ArrayList<Movie> getMoviesSaved(){
        String strJson = getSharedPreferences().getString( MOVIES, "-" );
        SearchMovies searchMovies = new SearchMovies();
        if ( strJson.equals( "-" ) )
            searchMovies.setMovies( new ArrayList<Movie>() );
        else
            searchMovies = new Gson().fromJson( strJson, SearchMovies.class );

        return searchMovies.getMovies();
    }

    private void saveJsonPreferences( String key, String stringJson )
    {
        getSharedPreferences().edit()
                .putString( key, stringJson )
                .commit();
    }

    private ArrayList<MovieDetails> getMoviesDetailsSaved(){
        String strJson = getSharedPreferences().getString( MOVIES_DETAILS, "-" );
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

    private void removeMovieDetails( Movie movie ){
        ArrayList<MovieDetails> moviesDetailsSaved = getMoviesDetailsSaved();
        int index = moviesDetailsSaved.indexOf( movie );
        if ( index != -1 ) {
            moviesDetailsSaved.remove( index );
            saveMovieDetails( moviesDetailsSaved );
        }
    }

    public void addMovieDetails( MovieDetails movieDetails ){
        ArrayList<MovieDetails> moviesDetailsSaved = getMoviesDetailsSaved();
        moviesDetailsSaved.add( 0, movieDetails );

        saveMovieDetails( moviesDetailsSaved );
    }

    private void saveMovieDetails( ArrayList<MovieDetails> moviesDetailsSaved ) {
        MoviesDetailsSaved listSaved = new MoviesDetailsSaved();
        listSaved.setMoviesSaved( moviesDetailsSaved );

        saveJsonPreferences( MOVIES_DETAILS, new Gson().toJson( listSaved ) );
    }

    public void removeMovie( Movie movie ) {
        ArrayList<Movie> moviesSaved = getMoviesSaved();
        moviesSaved.remove( movie );
        saveMovies( moviesSaved );
        removeMovieDetails( movie );
    }

    public void addMovie( Movie movie ){
        ArrayList<Movie> movies = getMoviesSaved();
        movies.add( 0, movie );
        saveMovies( movies );
    }

    private void saveMovies( ArrayList<Movie> movies ) {
        SearchMovies searchMovies = new SearchMovies();
        searchMovies.setMovies( movies );

        String strJson = new Gson().toJson( searchMovies );
        saveJsonPreferences( MOVIES, strJson );
    }

}
