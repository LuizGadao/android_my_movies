package com.luizgadao.testzup.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.luizgadao.testzup.R;
import com.luizgadao.testzup.model.SearchMovie;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luizcarlos on 26/08/15.
 */
public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {

    private List<SearchMovie> searchMovies;

    public AdapterSearch( List<SearchMovie> searchMovies ) {
        this.searchMovies = searchMovies;
    }

    public AdapterSearch() {

    }

    public void setSearchMovies( List<SearchMovie> searchMovies ) {
        this.searchMovies = searchMovies;
        this.notifyDataSetChanged();
    }

    public void clear(){
        if ( searchMovies != null ) {
            this.searchMovies.clear();
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
        holder.onBind( searchMovies.get( position ) );
    }

    @Override
    public int getItemCount() {
        return searchMovies != null ? searchMovies.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind( R.id.sdPicture )
        SimpleDraweeView picture;
        @Bind( R.id.tvTitleMovie )
        TextView title;
        @Bind( R.id.tvYearValue )
        TextView year;

        public ViewHolder( View itemView ) {
            super( itemView );
            ButterKnife.bind( this, itemView );
        }

        public void onBind( SearchMovie searchMovie ){
            title.setText( searchMovie.getTitle() );
            year.setText( searchMovie.getYear() );
            picture.setImageURI( Uri.parse( searchMovie.getPosterFromPosterAPI() ) );
        }

        @OnClick( R.id.ab_add_movie )
        public void clickButtonAddMovie(){
            Log.i( "ViewHolder Search", "click action button" );
        }
    }

}
