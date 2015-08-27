package com.luizgadao.testzup;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.luizgadao.testzup.model.Movie;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    public static final String MOVIE_SELECTED = "movie_selected";

    @Bind( R.id.collapsingToolbar )
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind( R.id.header )
    View header;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details );

        ButterKnife.bind( this );

        Movie movie = ( Movie ) getIntent().getSerializableExtra( MOVIE_SELECTED );
        collapsingToolbarLayout.setTitle( movie.Title );

        SimpleDraweeView picture = ( SimpleDraweeView ) header.findViewById( R.id.header_img );
        picture.setImageURI( Uri.parse( movie.getPosterFromPosterAPI() ) );
    }
}
