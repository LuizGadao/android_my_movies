package com.luizgadao.testzup.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by luizcarlos on 26/08/15.
 */
public class SearchMovies {

    @SerializedName( "Search" )
    private ArrayList<SearchMovie> searchMovies;

    public ArrayList<SearchMovie> getSearchMovies() {
        return searchMovies;
    }
}
