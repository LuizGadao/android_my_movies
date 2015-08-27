package com.luizgadao.testzup.model;

/**
 * Created by luizcarlos on 25/08/15.
 */
/*
{
Title: "Mad Max: Fury Road",
Year: "2015",
Rated: "R",
Released: "15 May 2015",
Runtime: "120 min",
Genre: "Action, Adventure, Sci-Fi",
Director: "George Miller",
Writer: "George Miller, Brendan McCarthy, Nick Lathouris",
Actors: "Tom Hardy, Charlize Theron, Nicholas Hoult, Hugh Keays-Byrne",
Plot: "An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and almost everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.",
Language: "English, Russian",
Country: "Australia, USA",
Awards: "2 nominations.",
Poster: "http://ia.media-imdb.com/images/M/MV5BMTUyMTE0ODcxNF5BMl5BanBnXkFtZTgwODE4NDQzNTE@._V1_SX300.jpg",
Metascore: "89",
imdbRating: "8.4",
imdbVotes: "263,276",
imdbID: "tt1392190",
Type: "movie",
Response: "True"
}
 */
public class MovieDetails extends Movie {

    private String Rated,
            Released, Runtime, Genre,
            Director, Writer, Actors,
            Plot, Language, Country,
            Awards, Poster, Metascore,
            imdbRating, imdbVotes, Response;

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public String getPoster() {
        return Poster;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getResponse() {
        return Response;
    }

    public String getPosterFromPosterAPI(){
        String apiKey = "b750eded";
        return String.format( "http://img.omdbapi.com/?apikey=%s&i=%s", apiKey, imdbID );
    }
}
