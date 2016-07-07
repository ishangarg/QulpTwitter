package com.qulp.qulptwitter.Request;

import com.qulp.qulptwitter.Model.TweetModel;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ishan-3306 on 7/6/2016.
 */
public interface Tweets {
    @GET("/1.1/search/tweets.json?result_type=mixed&count=10")

    void getTweets(@Query("q") String q, Callback<TweetModel> callback);
}
