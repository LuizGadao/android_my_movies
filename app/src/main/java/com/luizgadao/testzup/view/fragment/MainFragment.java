package com.luizgadao.testzup.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luizgadao.testzup.App;
import com.luizgadao.testzup.R;
import com.luizgadao.testzup.adapter.AdapterMovie;
import com.luizgadao.testzup.event.ItemRecyclerViewClick;
import com.luizgadao.testzup.model.Movie;
import com.luizgadao.testzup.utils.SharePreferecesUtils;
import com.luizgadao.testzup.view.DetailsActivity;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    @Bind( R.id.recyclerView )
    RecyclerView recyclerView;

    private AdapterMovie adapter;
    private ArrayList<Movie> moviesCached;

    public MainFragment() {
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_main, container, false);
        ButterKnife.bind( this, view );

        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize( false );
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new AdapterMovie();
        recyclerView.setAdapter( adapter );

        //setup swipe in recycler
        ItemTouchHelper.SimpleCallback swipe = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.i(TAG, "onSwiped");
                int pos = viewHolder.getAdapterPosition();
                adapter.remove(pos);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipe);
        itemTouchHelper.attachToRecyclerView(recyclerView);
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
        moviesCached = new SharePreferecesUtils().getMovies();
        Log.i( TAG, "movies-cached: " + moviesCached.size() );
        adapter.setSearchMovies( moviesCached );

        App.getInstance().getBus().register( this );
    }

    @Override
    public void onStop() {
        super.onStop();

        App.getInstance().getBus().unregister( this );
    }

    @Subscribe
    public void onItemRecyclerViewClick( ItemRecyclerViewClick event ){
        Movie movie = moviesCached.get( event.getPosition() );
        Log.i( TAG, "get - recyclerview clic title: " + movie.Title );
        Intent intent = new Intent( getActivity(), DetailsActivity.class );
        intent.putExtra( DetailsActivity.MOVIE_SELECTED, movie );
        startActivity( intent );
    }
}
