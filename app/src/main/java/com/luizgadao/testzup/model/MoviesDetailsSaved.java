package com.luizgadao.testzup.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by luizcarlos on 01/09/15.
 */
public class MoviesDetailsSaved {

    @SerializedName( "movies" )
    private ArrayList<MovieDetails> moviesSaved;

    public ArrayList<MovieDetails> getMoviesSaved() {
        return moviesSaved;
    }

    public void setMoviesSaved( ArrayList<MovieDetails> moviesSaved ) {
        this.moviesSaved = moviesSaved;
    }
}
