package com.luizgadao.testzup.adapter;

import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.luizgadao.testzup.App;
import com.luizgadao.testzup.R;
import com.luizgadao.testzup.model.Movie;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luizcarlos on 26/08/15.
 */
public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolder> {

    public static final String TYPE_PLUS = "+";
    public static final String TYPE_NOMAL = "normal";

    private List<Movie> movies;
    private String type = TYPE_NOMAL;

    public AdapterMovie( List<Movie> movies ) {
        this.movies = movies;
    }

    public AdapterMovie( String type ) {
        this.type = type;
    }

    public AdapterMovie() {
    }

    public void setSearchMovies( List<Movie> movies ) {
        this.movies = movies;
        this.notifyDataSetChanged();
    }

    public void clear(){
        if ( movies != null ) {
            this.movies.clear();
            this.notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_movie, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position ) {
        holder.onBind( movies.get( position ), type );
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = ViewHolder.class.getSimpleName();


        @Bind( R.id.sdPicture )
        SimpleDraweeView picture;
        @Bind( R.id.tvTitleMovie )
        TextView title;
        @Bind( R.id.tvYearValue )
        TextView year;
        @Bind( R.id.ab_add_movie )
        FloatingActionButton buttonAddMovie;

        Movie movie;

        public ViewHolder( View itemView ) {
            super( itemView );
            ButterKnife.bind( this, itemView );
        }

        public void onBind( Movie movie, String type ){
            this.movie = movie;
            title.setText( movie.Title );
            year.setText( movie.Year );
            picture.setImageURI( getUri() );

            if ( type.equals( TYPE_NOMAL ) )
                buttonAddMovie.setVisibility( View.GONE );
        }

        Uri getUri(){
            return Uri.parse( movie.getPosterFromPosterAPI() );
        }

        @OnClick( R.id.ab_add_movie )
        public void clickButtonAddMovie(){
            Log.i( "ViewHolder Search", "click action button" );
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            imagePipeline.evictFromMemoryCache( getUri() );

            App.getInstance().addMovie( movie );
            Snackbar.make( itemView, "Movie save!!!", Snackbar.LENGTH_SHORT )
                    .show();
        }
    }

}