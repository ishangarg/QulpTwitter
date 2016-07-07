package com.qulp.qulptwitter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qulp.qulptwitter.Model.Status;
import com.qulp.qulptwitter.Model.TweetModel;
import com.qulp.qulptwitter.Request.Tweets;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ishan-3306 on 7/6/2016.
 */
public class ActivityTweets extends AppCompatActivity{

    RecyclerView rv;
    ArrayList<Status> statusList;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        final String query = getIntent().getExtras().getString("term");
        rv = (RecyclerView) findViewById(R.id.rv_tweetList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new VerticalSpaceItemDecoration(15));

        statusList = new ArrayList<Status>();
        Util.showLoading(this,"Fetching tweets");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getTweets(query);
                handler.postDelayed(this,10000);
            }
        },500);
    }

    private void getTweets(String q){
        String query = "#"+q;

        final SharedPreferences sp = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        if(!sp.contains("access_token")){
            Intent i = new Intent(ActivityTweets.this,ActivitySearch.class);
            startActivity(i);
            finish();
        }


        Tweets tweets = ServiceGenerator.createService(Tweets.class,sp.getString("access_token",null));
        Log.d("TweetFetcher","Fetching tweets");
        tweets.getTweets(query, new Callback<TweetModel>() {
            @Override
            public void success(TweetModel tweetModel, Response response) {
                Log.d("Model",tweetModel.toString());
                statusList.addAll(tweetModel.getStatuses());
                TweetAdapter ta = new TweetAdapter(statusList);
                rv.setAdapter(ta);
                Util.hideLoading();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.d("RetrofitError",retrofitError.getMessage() + " " + retrofitError.getUrl() + " token = " + sp.getString("access_token",null));
                Toast.makeText(getApplicationContext(),"Error occurred while fetching tweets",Toast.LENGTH_SHORT).show();
                Util.hideLoading();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search:
            //    Intent i = new Intent(ActivityTweets.this,ActivitySearch.class);
              //  startActivity(i);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
