package com.qulp.qulptwitter;

import android.util.Base64;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by ishan-3306 on 7/6/2016.
 */
public class ServiceGenerator {
    public static final String TWITTER_BASE_URL = "https://api.twitter.com/";

    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(TWITTER_BASE_URL)
            .setClient(new OkClient(new OkHttpClient()));

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {
            // concatenate username and password with colon for authentication
            String credentials = username + ":" + password;
            // create Base64 encodet string
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestInterceptor.RequestFacade request) {
                    request.addHeader("Authorization", basic);
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
                }
            });
        }

        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass,final String token){
        final String bearer =
                "Bearer " + token;
        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                request.addHeader("Authorization", bearer);
                request.addHeader("Accept", "application/json");
            }
        });
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}
