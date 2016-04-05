package com.luizgadao.testzup.retrofit;

import com.luizgadao.testzup.model.MovieDetails;
import com.luizgadao.testzup.model.SearchMovies;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Asus Online on 16/10/2015.
 */
public interface OmdbAPI {

    @GET("?type=movie")
    Call<SearchMovies> searchMovies(@Query("s") String query);

    @GET("?plot=full&r=json")
    Call<MovieDetails> getMovied(@Query("i") String idMovie);
}
