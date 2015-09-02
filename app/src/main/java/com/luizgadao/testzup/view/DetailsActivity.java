package com.luizgadao.testzup.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.luizgadao.testzup.R;
import com.luizgadao.testzup.model.Movie;
import com.luizgadao.testzup.view.fragment.DetailsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    public static final String MOVIE_SELECTED = "movie_selected";
    private static final String FRAGMENT_TAG = "details_fragment";

    @Bind( R.id.collapsingToolbar )
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind( R.id.header )
    View header;
    @Bind( R.id.toolbar )
    Toolbar toolbar;
    private Fragment fragment;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details );

        ButterKnife.bind( this );

        setSupportActionBar( toolbar );
        getSupportActionBar().setHomeButtonEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        Movie movie = ( Movie ) getIntent().getSerializableExtra( MOVIE_SELECTED );
        collapsingToolbarLayout.setTitle( movie.Title );

        SimpleDraweeView picture = ( SimpleDraweeView ) header.findViewById( R.id.header_img );
        picture.setImageURI( Uri.parse( movie.getPosterFromPosterAPI() ) );

        //setup fragment
        if( savedInstanceState != null )
            fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        else
            fragment = new DetailsFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.details_container, fragment, FRAGMENT_TAG)
                .commit();

    }
}
