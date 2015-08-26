package com.luizgadao.testzup.model;

/**
 * Created by luizcarlos on 24/08/15.
 */
public class SearchMovie {

    private String Title, Year, imdbID, Type;

    public SearchMovie( String title, String year, String imdbID, String type ) {
        Title = title;
        Year = year;
        this.imdbID = imdbID;
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle( String title ) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear( String year ) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID( String imdbID ) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType( String type ) {
        Type = type;
    }

    public String getPosterFromPosterAPI(){
        String apiKey = "b750eded";
        return String.format( "http://img.omdbapi.com/?apikey=%s&i=%s", apiKey, imdbID );
    }
}
