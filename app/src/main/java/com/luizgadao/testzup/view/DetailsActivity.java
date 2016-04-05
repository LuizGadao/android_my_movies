package com.luizgadao.testzup.view;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.luizgadao.testzup.R;
import com.luizgadao.testzup.model.Movie;
import com.luizgadao.testzup.view.fragment.DetailsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    public static final String MOVIE_SELECTED = "movie_selected";
    private static final String FRAGMENT_TAG = "details_fragment";
    private static final String TAG = DetailsActivity.class.getSimpleName();

    @Bind( R.id.collapsingToolbar )
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind( R.id.header )
    View header;
    @Bind( R.id.toolbar )
    Toolbar toolbar;

    private Fragment fragment;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        collapsingToolbarLayout.setContentScrimColor(Color.TRANSPARENT);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = ( Movie ) getIntent().getSerializableExtra(MOVIE_SELECTED);
        collapsingToolbarLayout.setTitle( movie.Title );

        SimpleDraweeView picture = ( SimpleDraweeView ) header.findViewById( R.id.header_img );
        Uri uriPicture = Uri.parse(movie.getPosterFromPosterAPI());
        picture.setImageURI(uriPicture);

        //getBitmapFresco(uriPicture);

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

    private void getBitmapFresco(Uri uri){
        ImagePipeline imagePipeline = Fresco.getImagePipeline();

        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setRequestPriority(Priority.HIGH)
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .build();

        DataSource<CloseableReference<CloseableImage>> dataSource =
                imagePipeline.fetchDecodedImage(imageRequest, this);

        try {
            dataSource.subscribe(new BaseBitmapDataSubscriber() {
                @Override
                public void onNewResultImpl(@Nullable Bitmap bitmap) {
                    if (bitmap == null) {
                        Log.d(TAG, "Bitmap data source returned success, but bitmap null.");
                        return;
                    }
                    // The bitmap provided to this method is only guaranteed to be around
                    // for the lifespan of this method. The image pipeline frees the
                    // bitmap's memory after this method has completed.
                    //
                    // This is fine when passing the bitmap to a system process as
                    // Android automatically creates a copy.
                    //
                    // If you need to keep the bitmap around, look into using a
                    // BaseDataSubscriber instead of a BaseBitmapDataSubscriber.

                    setupPallete(bitmap);
                }

                @Override
                public void onFailureImpl(DataSource dataSource) {
                    // No cleanup required here
                }
            }, CallerThreadExecutor.getInstance());
        } finally {
            if (dataSource != null) {
                dataSource.close();
            }
        }
    }

    private void setupPallete(Bitmap bmp) {
        Palette palette = Palette.from(bmp).generate();
        int defaultColor = Color.parseColor("#673AB7");
        int mutedColor = palette.getMutedColor(defaultColor);
        collapsingToolbarLayout.setContentScrimColor(mutedColor);
        //collapsingToolbarLayout.setBackgroundColor(mutedColor);
        //toolbar.setBackgroundColor(mutedColor);
    }
}
