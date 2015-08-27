package com.luizgadao.testzup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luizgadao.testzup.adapter.AdapterMovie;
import com.luizgadao.testzup.model.Movie;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    @Bind( R.id.recyclerView )
    RecyclerView recyclerView;

    private AdapterMovie adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_main, container, false );
        ButterKnife.bind( this, view );

        recyclerView.setHasFixedSize( false );
        LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false );
        recyclerView.setLayoutManager( layoutManager );
        adapter = new AdapterMovie();
        recyclerView.setAdapter( adapter );

        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState ) {
        super.onActivityCreated( savedInstanceState );

        FloatingActionButton fabAddMovie = ( FloatingActionButton ) getActivity().findViewById( R.id.fab_add );
        if ( fabAddMovie != null )
            fabAddMovie.attachToRecyclerView( recyclerView );
    }

    @Override
    public void onResume() {
        super.onResume();

        adapter.clear();
        List<Movie> moviesCached = App.getInstance().getMovies();
        Log.i( TAG, "movies-cached: " + moviesCached.size() );
        adapter.setSearchMovies( moviesCached );
    }
}
