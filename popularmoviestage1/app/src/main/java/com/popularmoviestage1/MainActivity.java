package com.popularmoviestage1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


   //public String LOG_TAG = "Fragment";
    public static ArrayList<Movie> movies = new ArrayList<Movie>();
    private RequestQueue mRequestQueue;
    public MovieImageAdapter mImgAdapter;
    public String sortingOrder = "popularity";
    GridView gridview;
    String ratingUrl = "http://api.themoviedb.org/3/movie/top_rated?api_key=";
    String popularUrl = "http://api.themoviedb.org/3/movie/popular?api_key=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        mRequestQueue = Volley.newRequestQueue(this);
       // setup image adapter
        movies.clear();
        mImgAdapter = new MovieImageAdapter(this);
        refresh(sortingOrder);
        gridview = (GridView) findViewById(R.id.gridView);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MovieDetailsActivity.class).putExtra(Intent.EXTRA_TEXT, position + "");
                startActivity(intent);
            }
        });
    }

    public void refresh(String sortingOrder){
         if(movies.size() == 0) {
             if(sortingOrder == "rating") {
                 fetchMovies(ratingUrl);
             }else{
                 fetchMovies(popularUrl);
             }

         }
           else {
               resetImageAdapter();

        }

    }
    public void resetImageAdapter(){
        mImgAdapter.clearItems();
        for (int i = 0; i < movies.size(); i++)
            mImgAdapter.addItem(movies.get(i).poster_url);
    }

    public void fetchMovies(String url){

        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray resItems = response.getJSONArray("results");
                            JSONObject movieObj;

                            for (int i=0; i<resItems.length(); i++){
                                movieObj = resItems.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.id = movieObj.getInt("id");
                                movie.display_name = movieObj.getString("original_title");
                                movie.overview = movieObj.getString("overview");
                                movie.poster_url = "http://image.tmdb.org/t/p/w185/" + movieObj.getString("poster_path");
                                movie.released_date = movieObj.getString("release_date");
                                movie.rating = (float) movieObj.getDouble("vote_average");
                                movie.popularity = movieObj.getDouble("popularity");
                                movies.add(movie);
                                // Add image to adapter
                                mImgAdapter.addItem(movie.poster_url);
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        gridview.setAdapter(mImgAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Toast.makeText(getApplicationContext(),)error.toString());
                //Log.d(LOG_TAG, "Error in JSON Parsing");
            }
        });

         mRequestQueue.add(request);
    }



    public void setGridColCount(int n){
        ((GridView) findViewById(R.id.gridView)).setNumColumns(n);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if(id == R.id.action_sort_rating){
            sortingOrder = "rating";
            movies.clear();
            refresh(sortingOrder);
            resetImageAdapter();
            return true;
        }else if(id == R.id.action_sort_popularity){
            sortingOrder = "popularity";
            movies.clear();
            refresh(sortingOrder);
            resetImageAdapter();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}


