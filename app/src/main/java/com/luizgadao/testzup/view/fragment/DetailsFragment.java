package com.luizgadao.testzup.view.fragment;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.luizgadao.testzup.R;
import com.luizgadao.testzup.model.Movie;
import com.luizgadao.testzup.model.MovieDetails;
import com.luizgadao.testzup.retrofit.OmdbAPI;
import com.luizgadao.testzup.utils.SharePreferecesUtils;
import com.luizgadao.testzup.view.DetailsActivity;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsFragment extends Fragment {


    private static final String TAG = DetailsActivity.class.getSimpleName();


    @Bind( R.id.tv_directors_value )
    TextView tvDirectors;
    @Bind( R.id.tv_writers_value )
    TextView tvWriters;
    @Bind( R.id.tv_actors_value )
    TextView tvActors;
    @Bind( R.id.tv_award_value )
    TextView tvAward;
    @Bind( R.id.tv_language_value )
    TextView tvLanguage;
    @Bind( R.id.tv_released_value )
    TextView tvReleased;
    @Bind( R.id.tv_description_value )
    TextView tvDescription;
    @Bind( R.id.progress_view )
    CircularProgressView progress;
    @BindString( R.string.api_movie_details )
    String urlDetails;


    CoordinatorLayout.Behavior behavior;
    private Movie movie;
    private Retrofit retrofit;
    private OmdbAPI omdbAPI;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {

        View view = inflater.inflate( R.layout.fragment_details, container, false );
        ButterKnife.bind(this, view);
        movie = ( Movie ) getActivity().getIntent().getSerializableExtra( DetailsActivity.MOVIE_SELECTED );
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        MovieDetails movieDetails = new SharePreferecesUtils().getMovieDetails(movie);
        if (  movieDetails != null )
            setDataMovie( movieDetails );
        else
            loadDetailsMovie();
    }

    private void setDataMovie( MovieDetails movieDetails ) {
        tvDirectors.setText( movieDetails.getDirector() );
        tvWriters.setText( movieDetails.getWriter() );
        tvActors.setText( movieDetails.getActors() );
        tvAward.setText( movieDetails.getAwards() );
        tvLanguage.setText( movieDetails.getLanguage() );
        tvReleased.setText( movieDetails.getReleased() );
        tvDescription.setText( movieDetails.getPlot() );

        //setup rating movie
        View header = getActivity().findViewById( R.id.header );
        if ( header != null ){
            TextView tvCurrentRating = ( TextView ) header.findViewById( R.id.current_rating );
            tvCurrentRating.setText( movieDetails.getImdbRating() );

            TextView tvMaxRating = ( TextView ) header.findViewById( R.id.max_rating );
            tvMaxRating.setVisibility( View.VISIBLE );
        }
    }

    private void loadDetailsMovie() {
        if (retrofit == null) {
            setupRetrofit();
        }

        if (progress.getVisibility() == View.GONE)
            progress.setVisibility(View.VISIBLE);

        Call<MovieDetails> callMovieDetails = omdbAPI.getMovied(movie.imdbID);
        callMovieDetails.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(retrofit.Response<MovieDetails> response, Retrofit retrofit) {
                progress.setVisibility( View.GONE );
                MovieDetails movieDetails = response.body();
                setDataMovie(movieDetails);
                new SharePreferecesUtils().addMovieDetails(movieDetails);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setupRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        omdbAPI = retrofit.create(OmdbAPI.class);
    }
}
