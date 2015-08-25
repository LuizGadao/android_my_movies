package com.luizgadao.testzup.model;

import java.util.ArrayList;

/**
 * Created by luizcarlos on 24/08/15.
 */
public class SearchMovie {

    private ArrayList<SearchMovie> Search = new ArrayList<>();

    private String Title, Year, imdbID, Type;

    public SearchMovie( String title, String year, String imdbID, String type ) {
        Title = title;
        Year = year;
        this.imdbID = imdbID;
        Type = type;
    }

    public ArrayList<SearchMovie> getSearch() {
        return Search;
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
}
