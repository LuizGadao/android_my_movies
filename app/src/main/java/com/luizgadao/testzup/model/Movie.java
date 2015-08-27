package com.luizgadao.testzup.model;

/**
 * Created by luizcarlos on 24/08/15.
 */
public class Movie {

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
}
