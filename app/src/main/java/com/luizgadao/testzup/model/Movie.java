package com.luizgadao.testzup.model;

import java.io.Serializable;

/**
 * Created by luizcarlos on 24/08/15.
 */
public class Movie implements Serializable {

    public String Title, Year, imdbID, Type;

    public Movie() {
    }

    public Movie( String title, String year, String imdbID, String type ) {
        Title = title;
        Year = year;
        this.imdbID = imdbID;
        Type = type;
    }
    public String getPosterFromPosterAPI(){
        String apiKey = "b750eded";
        return String.format( "http://img.omdbapi.com/?apikey=%s&i=%s", apiKey, imdbID );
    }

    @Override
    public boolean equals( Object o ) {
        Movie m = ( Movie ) o;

        return imdbID.equals( m.imdbID );
    }
}
