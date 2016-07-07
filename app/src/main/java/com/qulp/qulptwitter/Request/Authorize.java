package com.qulp.qulptwitter.Request;

import com.qulp.qulptwitter.Model.AuthorizationModel;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by ishan-3306 on 7/6/2016.
 */
public interface Authorize {
    @FormUrlEncoded
    @POST("/oauth2/token")

    void authorizeApp(@Field("grant_type") String c, Callback<AuthorizationModel> callback);
}
