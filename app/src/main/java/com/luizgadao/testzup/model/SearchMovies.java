package com.luizgadao.testzup.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by luizcarlos on 26/08/15.
 */
public class SearchMovies {

    @SerializedName( "Search" )
    private ArrayList<Movie> movies;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies( ArrayList<Movie> movies ) {
        this.movies = movies;
    }
}
