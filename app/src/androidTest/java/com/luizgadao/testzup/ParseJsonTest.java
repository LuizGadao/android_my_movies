package com.luizgadao.testzup;

import android.util.Log;

import com.google.gson.Gson;
import com.luizgadao.testzup.model.MovieDetails;
import com.luizgadao.testzup.model.SearchMovie;
import com.luizgadao.testzup.model.SearchMovies;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by luizcarlos on 24/08/15.
 */
public class ParseJsonTest {

    private static String TAG = ParseJsonTest.class.getSimpleName();

    @Test
    public void jsonToSearchMovie(){
        String json = "{\"Title\":\"Mad Max: Fury Road\",\"Year\":\"2015\",\"imdbID\":\"tt1392190\",\"Type\":\"movie\"}";
        Gson gson = new Gson();
        SearchMovie searchMovie = gson.fromJson( json, SearchMovie.class );
        Assert.assertTrue( searchMovie.getImdbID().equals( "tt1392190" ) );
    }

    @Test
    public void jsonToCollectionSearchMovie(){
        String json = "{\"Search\":[{\"Title\":\"Mad Max: Fury Road\",\"Year\":\"2015\",\"imdbID\":\"tt1392190\",\"Type\":\"movie\"},{\"Title\":\"Mad Max\",\"Year\":\"1979\",\"imdbID\":\"tt0079501\",\"Type\":\"movie\"},{\"Title\":\"Mad Max 2: The Road Warrior\",\"Year\":\"1981\",\"imdbID\":\"tt0082694\",\"Type\":\"movie\"},{\"Title\":\"Mad Max Beyond Thunderdome\",\"Year\":\"1985\",\"imdbID\":\"tt0089530\",\"Type\":\"movie\"},{\"Title\":\"Mad Max Renegade\",\"Year\":\"2011\",\"imdbID\":\"tt2011110\",\"Type\":\"movie\"},{\"Title\":\"Mad Max: The Film Phenomenon\",\"Year\":\"2002\",\"imdbID\":\"tt0376739\",\"Type\":\"movie\"},{\"Title\":\"Mad 2 the Max\",\"Year\":\"2013\",\"imdbID\":\"tt2771034\",\"Type\":\"movie\"},{\"Title\":\"Mad Max Sweded\",\"Year\":\"2010\",\"imdbID\":\"tt3274336\",\"Type\":\"movie\"},{\"Title\":\"Tampax: Mad Max - Protector\",\"Year\":\"2015\",\"imdbID\":\"tt4713352\",\"Type\":\"movie\"},{\"Title\":\"Mad Max: Roadkill BBQ\",\"Year\":\"2015\",\"imdbID\":\"tt4947444\",\"Type\":\"movie\"}]}";
        Gson gson = new Gson();
        SearchMovies searchMovies = gson.fromJson( json, SearchMovies.class );
        Log.i( TAG, "total movies: " + searchMovies.getSearchMovies().size() );
        Assert.assertTrue( searchMovies.getSearchMovies().size() == 10 );
    }

    @Test
    public void jsonToMovie(){
        String json = "{\n" +
                "Title: \"Mad Max: Fury Road\",\n" +
                "Year: \"2015\",\n" +
                "Rated: \"R\",\n" +
                "Released: \"15 May 2015\",\n" +
                "Runtime: \"120 min\",\n" +
                "Genre: \"Action, Adventure, Sci-Fi\",\n" +
                "Director: \"George Miller\",\n" +
                "Writer: \"George Miller, Brendan McCarthy, Nick Lathouris\",\n" +
                "Actors: \"Tom Hardy, Charlize Theron, Nicholas Hoult, Hugh Keays-Byrne\",\n" +
                "Plot: \"An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and almost everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.\",\n" +
                "Language: \"English, Russian\",\n" +
                "Country: \"Australia, USA\",\n" +
                "Awards: \"2 nominations.\",\n" +
                "Poster: \"http://ia.media-imdb.com/images/M/MV5BMTUyMTE0ODcxNF5BMl5BanBnXkFtZTgwODE4NDQzNTE@._V1_SX300.jpg\",\n" +
                "Metascore: \"89\",\n" +
                "imdbRating: \"8.4\",\n" +
                "imdbVotes: \"263,276\",\n" +
                "imdbID: \"tt1392190\",\n" +
                "Type: \"movie\",\n" +
                "Response: \"True\"\n" +
                "}";
        MovieDetails movie = new Gson().fromJson( json, MovieDetails.class );
        Log.i( TAG, "poster api: " + movie.getPosterFromPosterAPI() );
        Assert.assertTrue( movie.getTitle().equals( "Mad Max: Fury Road" ) );
    }

}
