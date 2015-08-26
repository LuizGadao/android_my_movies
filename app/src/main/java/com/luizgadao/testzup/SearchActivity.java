package com.luizgadao.testzup;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.luizgadao.testzup.model.SearchMovie;
import com.luizgadao.testzup.model.SearchMovies;
import com.luizgadao.testzup.network.GsonRequest;
import com.luizgadao.testzup.network.VolleyHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = SearchActivity.class.getSimpleName();
    private static final String TAG_REQUEST = "tag-request";

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );

        ButterKnife.bind( this );

        //setup toolbar
        setSupportActionBar( toolbar );
        getSupportActionBar().setHomeButtonEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        handleIntent( getIntent() );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_search, menu );

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService( Context.SEARCH_SERVICE );
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo( searchManager.getSearchableInfo( getComponentName() ) );
        searchView.setQueryHint( getResources().getString( R.string.search_hint ) );
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit( String query ) {
                return false;
            }

            @Override
            public boolean onQueryTextChange( String newText ) {
                search( newText );
                return false;
            }
        } );

        return true;
    }

    @Override
    protected void onNewIntent( Intent intent ) {
        setIntent( intent );
        handleIntent( intent );
    }

    public void handleIntent( Intent intent ) {

        if (Intent.ACTION_SEARCH.equalsIgnoreCase( intent.getAction() )) {
            String query = intent.getStringExtra( SearchManager.QUERY );
            search( query );
        }
    }

    public void search( String query ) {
        VolleyHelper volley = VolleyHelper.getInstance( getApplicationContext() );
        volley.removeRequestQueue( TAG_REQUEST );
        try {
            query = URLEncoder.encode( query, "UTF-8" );
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace();
        }

        String searchUrl = String.format( getResources().getString( R.string.api_search ), query );
        Log.i( TAG, "search for: " + searchUrl );
        GsonRequest gsonRequest = new GsonRequest( searchUrl, SearchMovies.class, null,
                loadMoviesSuccess(), errorLoadMovies() );

        gsonRequest.setTag( TAG_REQUEST );
        volley.addRequestQueue( gsonRequest );
    }

    private Response.Listener<SearchMovies> loadMoviesSuccess(){
        return new Response.Listener<SearchMovies>() {
            @Override
            public void onResponse( SearchMovies movies ) {
                if ( movies != null & movies.getSearchMovies() != null ){
                    Log.i( TAG, "search movies count: " + movies.getSearchMovies().size() );

                    for ( int i = 0; i < movies.getSearchMovies().size(); i++ ){
                        SearchMovie movie = movies.getSearchMovies().get( i );
                        Log.i( TAG, "title-movie: " + movie.getTitle() + " img: " + movie.getPosterFromPosterAPI() );
                    }
                }
            }
        };
    }

    private Response.ErrorListener errorLoadMovies(){
        return new Response.ErrorListener() {
                @Override
                public void onErrorResponse( VolleyError error ) {
                    error.printStackTrace();
                }
        };
    }

    /*
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings ) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }
    */
}
