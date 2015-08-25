package com.luizgadao.testzup;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        return inflater.inflate( R.layout.fragment_main, container, false );
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );

        FloatingActionButton buttonAddMovie = ( FloatingActionButton ) view.findViewById( R.id.ab_add_movie );
        buttonAddMovie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Log.i(TAG, "click floating action button");
            }
        } );
    }
}
